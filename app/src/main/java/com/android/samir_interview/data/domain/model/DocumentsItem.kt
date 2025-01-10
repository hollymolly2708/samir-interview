package com.android.samir_interview.data.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class DocumentsItem(
    val type: String? = null,
    val url: String? = null
):Parcelable
