package com.android.samir_interview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.samir_interview.data.domain.usecase.LoanUseCase

class ViewModelFactory(private val loanUseCase: LoanUseCase) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        var INSTANCE: ViewModelFactory? = null
        fun getInstance(loanUseCase: LoanUseCase): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE =
                        ViewModelFactory(loanUseCase)
                }

            }
            return INSTANCE!!
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(LoanViewModel::class.java) -> {
                return LoanViewModel(loanUseCase) as T
            }

            else -> {
                throw Throwable("Unknown Viewmodel class : " + modelClass.name)
            }
        }
    }
}