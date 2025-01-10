package com.android.samir_interview.data.domain.usecase

import com.android.samir_interview.Resource
import com.android.samir_interview.data.domain.model.Loan
import com.android.samir_interview.data.repository.LoanRepository

class LoanInteractor(private val loanRepository: LoanRepository) : LoanUseCase {
    override fun getDataLoans(callback: (Resource<List<Loan>>) -> Unit) {
        loanRepository.getDataLoans(callback)
    }

}