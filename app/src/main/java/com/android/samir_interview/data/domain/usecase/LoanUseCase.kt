package com.android.samir_interview.data.domain.usecase

import com.android.samir_interview.Resource
import com.android.samir_interview.data.domain.model.Loan

interface LoanUseCase {
    fun getDataLoans(callback: (Resource<List<Loan>>) -> Unit)
}