package com.android.samir_interview.data.domain.repository

import com.android.samir_interview.Resource
import com.android.samir_interview.data.domain.model.Loan

interface ILoanRepository {
    fun getDataLoans(callback: (Resource<List<Loan>>) -> Unit)
}