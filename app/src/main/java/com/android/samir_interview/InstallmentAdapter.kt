package com.android.samir_interview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.samir_interview.data.domain.model.InstallmentItem
import com.android.samir_interview.databinding.ItemListInstallmentBinding

class InstallmentAdapter(private val installmentItems: List<InstallmentItem>) :
    RecyclerView.Adapter<InstallmentAdapter.InstallmentViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): InstallmentAdapter.InstallmentViewHolder {
        val view = InstallmentViewHolder(
            ItemListInstallmentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        return view
    }

    override fun onBindViewHolder(holder: InstallmentAdapter.InstallmentViewHolder, position: Int) {
        val installmentItem = installmentItems[position]
        holder.binding.tvAmountDue.text = installmentItem.amountDue.toString()
        holder.binding.tvDueDate.text = installmentItem.dueDate.toString()
    }

    override fun getItemCount(): Int {
        return installmentItems.size
    }

    inner class InstallmentViewHolder(var binding: ItemListInstallmentBinding) :
        ViewHolder(binding.root)
}