package com.android.samir_interview.data.source.remote

import android.util.Log
import com.android.samir_interview.data.source.remote.network.ApiService
import com.nature_farm.android.samir_interview.LoanResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiService: ApiService) {
    companion object {
        @Volatile
        var INSTANCE: RemoteDataSource? = null
        fun getInstance(apiService: ApiService): RemoteDataSource {

            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = RemoteDataSource(apiService)
                }

            }
            return INSTANCE!!
        }
    }

    fun getDataLoans(callback: (ApiResponse<List<LoanResponse>?>) -> Unit) {
        apiService.getDataLoans().enqueue(object : Callback<List<LoanResponse>> {
            override fun onResponse(
                call: Call<List<LoanResponse>>,
                response: Response<List<LoanResponse>>,
            ) {
                if (response.isSuccessful) {
                    val body = response.body()
                    if (body != null) {
                        callback(ApiResponse.Error(response.message()))
                    }
                    callback(ApiResponse.Success(body))
                } else {
                    callback(ApiResponse.Error(response.message()))
                }
            }

            override fun onFailure(call: Call<List<LoanResponse>>, t: Throwable) {
                Log.e("getDataLoansFailure", t.message.toString())
                callback(ApiResponse.Error(t.message.toString()))
            }

        })
    }
}