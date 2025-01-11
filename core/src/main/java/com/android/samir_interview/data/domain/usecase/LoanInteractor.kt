package com.android.samir_interview.data.domain.usecase

import com.android.samir_interview.data.Resource
import com.android.samir_interview.data.domain.model.Loan
import com.android.samir_interview.data.repository.LoanRepository
import kotlinx.coroutines.flow.Flow

class LoanInteractor(private val loanRepository: LoanRepository) : LoanUseCase {
    override suspend fun getDataLoans(): Flow<Resource<List<Loan>>> {
       return loanRepository.getDataLoans()
    }

}