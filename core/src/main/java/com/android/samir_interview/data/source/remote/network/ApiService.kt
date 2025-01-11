package com.android.samir_interview.data.source.remote.network

import com.nature_farm.android.samir_interview.LoanResponse
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("loans.json")
    fun getDataLoans() : Call<List<LoanResponse>>
}