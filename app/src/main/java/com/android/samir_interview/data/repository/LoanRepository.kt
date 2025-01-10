package com.android.samir_interview.data.repository

import com.android.samir_interview.Resource
import com.android.samir_interview.data.domain.model.Loan
import com.android.samir_interview.data.domain.repository.ILoanRepository
import com.android.samir_interview.data.mapper.LoanMapper
import com.android.samir_interview.data.source.remote.ApiResponse
import com.android.samir_interview.data.source.remote.RemoteDataSource

class LoanRepository private constructor(private val remoteDataSource: RemoteDataSource) :
    ILoanRepository {
    companion object {
        @Volatile
        var INSTANCE: LoanRepository? = null
        fun getInstance(remoteDataSource: RemoteDataSource): LoanRepository {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = LoanRepository(remoteDataSource)
                }

            }
            return INSTANCE!!
        }
    }

    override fun getDataLoans(callback: (Resource<List<Loan>>) -> Unit) {
        remoteDataSource.getDataLoans { apiResponse ->
            when (apiResponse) {
                is ApiResponse.Success -> {
                    apiResponse.data?.let {

                        val loanResponses = it
                        val loans = LoanMapper.LoanResponsesToLoans(loanResponses)

                        callback(Resource.Success(loans))
                    }
                }

                is ApiResponse.Error -> {
                    apiResponse.errorMessage?.let {
                        callback(Resource.Error(it))
                    }
                }

                is ApiResponse.Empty -> {
                    callback(Resource.Error("no data available"))
                }

            }
        }
    }


}