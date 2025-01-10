package com.android.samir_interview.data.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RepaymentSchedule(
    val installments: List<InstallmentsItem>? = null
): Parcelable
