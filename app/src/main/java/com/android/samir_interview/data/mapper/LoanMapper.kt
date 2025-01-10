package com.android.samir_interview.data.mapper

import com.android.samir_interview.data.domain.model.Borrower
import com.android.samir_interview.data.domain.model.Collateral
import com.android.samir_interview.data.domain.model.DocumentsItem
import com.android.samir_interview.data.domain.model.InstallmentsItem
import com.android.samir_interview.data.domain.model.Loan
import com.android.samir_interview.data.domain.model.RepaymentSchedule
import com.android.samir_interview.data.source.remote.response.RepaymentScheduleResponse
import com.nature_farm.android.samir_interview.LoanResponse
import java.util.stream.Collectors

object LoanMapper {
    fun LoanResponsesToLoans(loanResponses: List<LoanResponse>): List<Loan> {
        val loans = loanResponses.stream().map {
            val loanResponse = it
            val documentsItem = it.documents?.stream()
                ?.map { document -> DocumentsItem(type = document?.type, url = document?.url) }
                ?.collect(Collectors.toList())
            val borrower = Borrower(
                creditScore = loanResponse.borrower?.creditScore,
                name = loanResponse.borrower?.name,
                id = loanResponse.borrower?.id,
                email = loanResponse.borrower?.email
            )

            val collateral = Collateral(
                type = loanResponse.collateral?.type,
                value = loanResponse.collateral?.value
            )

            val installmentsItem =
                loanResponse.repaymentSchedule?.installments?.stream()?.map {
                    InstallmentsItem(amountDue = it.amountDue, dueDate = it.dueDate)
                }?.collect(Collectors.toList())

            val repaymentSchedule = RepaymentSchedule(installments = installmentsItem)
            return@map Loan(
                interestRate = loanResponse.interestRate,
                amount = loanResponse.amount,
                purpose = loanResponse.purpose,
                documents = documentsItem,
                borrower = borrower,
                term = loanResponse.term,
                id = loanResponse.id,
                collateral = collateral,
                repaymentSchedule = repaymentSchedule,
                riskRating = loanResponse.riskRating
            )
        }.collect(Collectors.toList())

        return loans
    }
}