package com.android.samir_interview

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.samir_interview.data.domain.model.Loan
import com.android.samir_interview.data.source.remote.di.Injector
import com.android.samir_interview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: LoanViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val factory = Injector.provideViewModelFactory()
        viewModel = ViewModelProvider(this@MainActivity, factory)[LoanViewModel::class.java]
        getDataLoans()
        setupObservers()

    }

    private fun setupObservers() {
        viewModel.loan.observe(this) {
            val adapter = LoanAdapter(it)
            binding.loanListRecyclerView.adapter = adapter
            binding.loanListRecyclerView.layoutManager = LinearLayoutManager(this)
            binding.loanListRecyclerView.setHasFixedSize(true)

            adapter.onItemClickCallback(object : LoanAdapter.OnItemClickCallback {
                override fun onClicked(loan: Loan) {
                    val intent = Intent(this@MainActivity, DetailActivity::class.java)
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