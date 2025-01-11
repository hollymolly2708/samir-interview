package com.android.samir_interview.data.source.remote.di


import com.android.samir_interview.data.domain.usecase.LoanInteractor
import com.android.samir_interview.data.domain.usecase.LoanUseCase
import com.android.samir_interview.data.repository.LoanRepository
import com.android.samir_interview.data.source.remote.RemoteDataSource
import com.android.samir_interview.data.source.remote.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Injector {

    private fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun provideApiService(): ApiService {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/andreascandle/p2p_json_test/main/api/json/")
            .client(provideOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    private fun provideRemoteDataSource(): RemoteDataSource {
        return RemoteDataSource.getInstance(provideApiService())
    }

    private fun provideLoanRepository(): LoanRepository {
        return LoanRepository.getInstance(provideRemoteDataSource())
    }

    fun provideLoanInteractor(): LoanUseCase {
        return LoanInteractor(provideLoanRepository())
    }



}