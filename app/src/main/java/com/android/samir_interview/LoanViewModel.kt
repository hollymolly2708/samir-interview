package com.android.samir_interview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.samir_interview.data.domain.model.Loan
import com.android.samir_interview.data.domain.usecase.LoanUseCase

class LoanViewModel(private val loanUseCase: LoanUseCase) : ViewModel() {
    private val _loans: MutableLiveData<List<Loan>> = MutableLiveData()
    val loan: LiveData<List<Loan>> = _loans

    private val _message: MutableLiveData<String> = MutableLiveData()
    val message: LiveData<String> = _message

    fun getLoans() {
        loanUseCase.getDataLoans { resource ->
            when (resource) {
                is Resource.Success -> {
                    resource.data?.let {
                        _loans.value = it
                    }
                }

                is Resource.Error -> {
                    resource.message?.let { _message.value = it }
                }

                is Resource.Loading -> {

                }
            }
        }
    }
}