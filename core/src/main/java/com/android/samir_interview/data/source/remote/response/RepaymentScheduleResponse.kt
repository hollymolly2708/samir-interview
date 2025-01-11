package com.android.samir_interview.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class RepaymentScheduleResponse(
    @field:SerializedName("installments")
    val installments: List<InstallmentsItemResponse>? = null
)