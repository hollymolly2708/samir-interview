package com.android.samir_interview.data.source.remote

import android.util.DisplayMetrics
import android.util.Log
import com.android.samir_interview.data.domain.model.Loan
import com.android.samir_interview.data.source.remote.network.ApiService
import com.nature_farm.android.samir_interview.LoanResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        var INSTANCE: RemoteDataSource? = null
        fun getInstance(apiService: ApiService): RemoteDataSource {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = RemoteDataSource(apiService)
                }

            }
            return INSTANCE!!
        }
    }

    suspend fun getDataLoans(): Flow<ApiResponse<List<LoanResponse>>> {


        return flow {
            try {
                val response = apiService.getDataLoans()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}