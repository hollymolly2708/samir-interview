package com.android.samir_interview.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class BorrowerResponse(

    @field:SerializedName("creditScore")
    val creditScore: Int? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("email")
    val email: String? = null
)
