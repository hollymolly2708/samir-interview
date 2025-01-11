package com.android.samir_interview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.android.samir_interview.data.domain.model.DocumentsItem
import com.android.samir_interview.databinding.ItemListDocumentBinding

class DocumentAdapter(private val documents: List<DocumentsItem?>) :
    RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): DocumentAdapter.DocumentViewHolder {
        return DocumentViewHolder(
            ItemListDocumentBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return documents.size
    }

    override fun onBindViewHolder(holder: DocumentAdapter.DocumentViewHolder, position: Int) {
        val document = documents[position]
        holder.binding.tvDocumentId.setText((position + 1).toString() + ".")
        holder.binding.documentType.text = document?.type
        holder.binding.documentUrl.text = document?.url
    }

    inner class DocumentViewHolder(var binding: ItemListDocumentBinding) : ViewHolder(binding.root)
}