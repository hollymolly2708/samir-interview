package com.android.samir_interview

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.samir_interview.data.domain.model.Loan
import com.android.samir_interview.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val LOAN = "LOAN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val getLoanFromIntent = intent.extras?.get(LOAN) as Loan
        setupView(getLoanFromIntent)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }

            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }


    private fun setupView(loan: Loan) {
        val repaymentSchedule = loan.repaymentSchedule
        val installmentItem = repaymentSchedule?.installmentItems
        if (installmentItem != null) {
            val adapter = InstallmentAdapter(installmentItem)
            binding.rvInstallment.adapter = adapter
            binding.rvInstallment.layoutManager = LinearLayoutManager(this)
            binding.rvInstallment.setHasFixedSize(true)
        }

        val documents = loan.documents
        if (documents != null) {
            val adapter = DocumentAdapter(documents)
            binding.rvDocument.adapter = adapter
            binding.rvDocument.layoutManager = LinearLayoutManager(this)
            binding.rvDocument.setHasFixedSize(true)
            binding.tvNoDocument.visibility = View.GONE
            binding.rvDocument.visibility = View.VISIBLE
        } else {
            binding.tvNoDocument.visibility = View.VISIBLE
            binding.rvDocument.visibility = View.GONE
        }

        loan.let {
            binding.borrowerName.text = it.borrower?.name
            binding.borrowerEmail.text = it.borrower?.email
            binding.borrowerId.text = it.borrower?.id
            binding.borrowerCreditScore.text = "$" + it.borrower?.creditScore.toString()
            binding.loanId.text = it.id
            binding.loanAmount.text = "$" + it.amount.toString()
            binding.loanTerm.text = it.term.toString() + " " + "Bulan"
            binding.loanPurpose.text = it.purpose
            binding.loanInterestRate.text = it.interestRate.toString()
            binding.loanRiskRating.text = it.riskRating
            binding.collateralType.text = it.collateral?.type
            binding.collateralValue.text = "$" + it.collateral?.value.toString()


        }


    }


}