package com.nature_farm.android.samir_interview

import com.android.samir_interview.data.source.remote.response.BorrowerResponse
import com.android.samir_interview.data.source.remote.response.CollateralResponse
import com.android.samir_interview.data.source.remote.response.DocumentsItemResponse
import com.android.samir_interview.data.source.remote.response.RepaymentScheduleResponse
import com.google.gson.annotations.SerializedName

data class LoanResponse(

	@field:SerializedName("interestRate")
	val interestRate: Double? = null,

	@field:SerializedName("amount")
	val amount: Int? = null,

	@field:SerializedName("purpose")
	val purpose: String? = null,

	@field:SerializedName("documents")
	val documents: List<DocumentsItemResponse?>? = null,

	@field:SerializedName("borrower")
	val borrower: BorrowerResponse? = null,

	@field:SerializedName("term")
	val term: Int? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("collateral")
	val collateral: CollateralResponse? = null,

	@field:SerializedName("repaymentSchedule")
	val repaymentSchedule: RepaymentScheduleResponse? = null,

	@field:SerializedName("riskRating")
	val riskRating: String? = null
)






