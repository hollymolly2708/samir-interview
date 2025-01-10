package com.android.samir_interview.data.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Borrower(

    val creditScore: Int? = null,
    val name: String? = null,
    val id: String? = null,
    val email: String? = null
) : Parcelable
