package com.abdallahehab.bankapp.ui.transactionHistory

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdallahehab.bankapp.R
import com.abdallahehab.bankapp.databinding.TransactionHistoryFragmentBinding
import com.abdallahehab.bankapp.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TransactionHistoryFragment : Fragment() {

    companion object {
        fun newInstance() = TransactionHistoryFragment()
    }

   val viewModel: TransactionHistoryViewModel by viewModels()

    private lateinit var activity: MainActivity

    private lateinit var binding:TransactionHistoryFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        inflater.inflate(R.layout.transaction_history_fragment, container, false)

        binding = TransactionHistoryFragmentBinding.inflate(inflater)

        constructRecycleView()

        return binding.root
    }

    fun constructRecycleView(){
        val adaptor = TransactionHistoryAdaptor()

        viewModel.transactionsList.observe(this.viewLifecycleOwner, Observer {
            adaptor.dataList = it
            binding.transactonHistoryRecyclerView.adapter = adaptor
        })


        val layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)

        binding.transactonHistoryRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                layoutManager.orientation
            )
        )

        binding.transactonHistoryRecyclerView.layoutManager = layoutManager
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.activity = (activity as MainActivity)

    }

    override fun onResume() {
        super.onResume()
        activity.supportActionBar?.title="Transaction History"
    }

}