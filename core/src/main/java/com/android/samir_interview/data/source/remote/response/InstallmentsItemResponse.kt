package com.android.samir_interview.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class InstallmentsItemResponse(

    @field:SerializedName("amountDue")
    val amountDue: Int? = null,

    @field:SerializedName("dueDate")
    val dueDate: String? = null
)

