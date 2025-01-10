package com.android.samir_interview.data.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Loan(
    val interestRate: Double? = null,  // Menambahkan @RawValue di sini
    val amount: Int? = null,
    val purpose: String? = null,
    val documents: List<DocumentsItem?>? = null,
    val borrower:  Borrower? = null,
    val term: Int? = null,
    val id: String? = null,
    val collateral:  Collateral? = null,
    val repaymentSchedule:  RepaymentSchedule? = null,
    val riskRating: String? = null,
) : Parcelable
