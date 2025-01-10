package com.android.samir_interview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AUTOFILL_TYPE_LIST
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.samir_interview.data.domain.model.Loan
import com.android.samir_interview.databinding.ItemListLoanBinding

class LoanAdapter(private val loans: List<Loan>) :
    RecyclerView.Adapter<LoanAdapter.LoanViewHolder>() {


    private var onItemClickCallback: OnItemClickCallback? = null

    fun onItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LoanAdapter.LoanViewHolder {
        val view = LoanViewHolder(
            ItemListLoanBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return view
    }

    override fun onBindViewHolder(holder: LoanAdapter.LoanViewHolder, position: Int) {
        val loan = loans[position]
        val borrowerName = loan.borrower?.name
        holder.binding.borrowerName.text = borrowerName
        holder.binding.loanAmount.text = loan.amount.toString()
        holder.binding.loanTerm.text = loan.term.toString()
        holder.binding.purpose.text = loan.purpose
        holder.binding.interestRate.text = loan.interestRate.toString()
        holder.binding.riskRating.text = loan.riskRating

        holder.itemView.setOnClickListener {
            onItemClickCallback?.onClicked(loan)
        }
    }

    override fun getItemCount(): Int {
        return loans.size
    }

    inner class LoanViewHolder(var binding: ItemListLoanBinding) : ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onClicked(loan: Loan)
    }
}