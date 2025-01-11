package com.android.samir_interview.data.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class InstallmentItem(
    val amountDue: Int? = null,
    val dueDate: String? = null
) : Parcelable
