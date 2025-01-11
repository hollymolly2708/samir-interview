package com.android.samir_interview.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.samir_interview.DetailActivity
import com.android.samir_interview.LoanAdapter
import com.android.samir_interview.LoanViewModel
import com.android.samir_interview.data.domain.model.Loan
import com.android.samir_interview.data.source.remote.di.Injector
import com.android.samir_interview.databinding.FragmentHomeBinding
import com.android.samir_interview.di.AppInjector


class HomeFragment : Fragment() {

    private lateinit var viewModel: LoanViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val factory = AppInjector.provideViewModelFactory()
        viewModel = ViewModelProvider(requireActivity(), factory)[LoanViewModel::class.java]
        setupObservers()
        getDataLoans()
    }

    private fun searchListener(items: List<Loan>, adapter: LoanAdapter) {
        binding.searchProduct.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                if (query != null) {
                    val filteredData = items.filter {
                        it.borrower?.name!!.contains(query, true)
                    }

                    if (filteredData.isNotEmpty()) {
                        adapter.updateListData(filteredData)
                        return true
                    } else {
                        adapter.updateListData(filteredData)

                    }
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText != null) {
                    val filteredData = items.filter {
                        it.borrower?.name!!.contains(newText, true)

                    }

                    return if (filteredData.isNotEmpty()) {
                        adapter.updateListData(filteredData)
                        true
                    } else {
                        adapter.updateListData(filteredData)
                        false
                    }
                }
                return false
            }

        })
    }


    private fun setupObservers() {
        viewModel.loan.observe(viewLifecycleOwner) {
            val adapter = LoanAdapter(it)
            searchListener(it, adapter)
            binding.loanListRecyclerView.adapter = adapter
            binding.loanListRecyclerView.layoutManager = LinearLayoutManager(requireActivity())
            binding.loanListRecyclerView.setHasFixedSize(true)

            adapter.onItemClickCallback(object : LoanAdapter.OnItemClickCallback {
                override fun onClicked(loan: Loan) {
                    val intent = Intent(requireActivity(), DetailActivity::class.java)
                    intent.putExtra(DetailActivity.LOAN, loan)
                    startActivity(intent)
                }

            })
        }
    }

    private fun getDataLoans() {
        viewModel.getLoans()
    }
}