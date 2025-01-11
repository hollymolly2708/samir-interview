package com.android.samir_interview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.AUTOFILL_TYPE_LIST
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.samir_interview.data.domain.model.Loan
import com.android.samir_interview.databinding.ItemListLoanBinding

class LoanAdapter(private var loans: List<Loan>) :
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
        holder.binding.loanTerm.text = loan.term.toString() + " " + "Month"
        holder.binding.purpose.text = loan.purpose
        holder.binding.interestRate.text = loan.interestRate.toString()
        holder.binding.riskRating.text = loan.riskRating
        setupColor(holder, loan.riskRating)
        holder.itemView.setOnClickListener {
            onItemClickCallback?.onClicked(loan)
        }
    }

    override fun getItemCount(): Int {
        return loans.size
    }

    private fun setupColor(holder: LoanAdapter.LoanViewHolder, riskRating: String?) {
        if (riskRating == "A") {
            holder.binding.riskRating.setBackgroundColor(
                ContextCompat.getColor(
                    holder.binding.root.context,
                    android.R.color.holo_red_dark
                )
            )
        }
        if (riskRating == "B") {
            holder.binding.riskRating.setBackgroundColor(
                ContextCompat.getColor(
                    holder.binding.root.context,
                    R.color.dark_orange_2
                )
            )
            holder.binding.riskRating.setTextColor(
                ContextCompat.getColor(
                    holder.binding.root.context,
                    android.R.color.white
                )
            )
        }
        if (riskRating == "C") {
            holder.binding.riskRating.setBackgroundColor(
                ContextCompat.getColor(
                    holder.binding.root.context,
                    R.color.mint_green_80
                )
            )
            holder.binding.riskRating.setTextColor(
                ContextCompat.getColor(
                    holder.binding.root.context,
                    android.R.color.white
                )
            )
        }

    }

    inner class LoanViewHolder(var binding: ItemListLoanBinding) : ViewHolder(binding.root)

    interface OnItemClickCallback {
        fun onClicked(loan: Loan)
    }

    fun updateListData(newListData: List<Loan>) {
        this.loans = newListData.toMutableList()
        notifyDataSetChanged()
    }
}