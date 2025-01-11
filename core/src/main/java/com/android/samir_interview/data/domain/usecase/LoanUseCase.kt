package com.android.samir_interview.data.domain.usecase

import com.android.samir_interview.data.Resource
import com.android.samir_interview.data.domain.model.Loan
import kotlinx.coroutines.flow.Flow

interface LoanUseCase {
    suspend fun getDataLoans(): Flow<Resource<List<Loan>>>
}