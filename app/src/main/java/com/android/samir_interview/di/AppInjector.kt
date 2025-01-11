package com.android.samir_interview.di

import com.android.samir_interview.ViewModelFactory
import com.android.samir_interview.data.source.remote.di.Injector

object AppInjector {
    fun provideViewModelFactory(): ViewModelFactory {
        return ViewModelFactory(Injector.provideLoanInteractor())
    }
}