package com.android.samir_interview.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class CollateralResponse(

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("value")
    val value: Int? = null
)
