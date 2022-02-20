package com.abdallahehab.bankapp.ui.home

import android.app.Activity
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.abdallahehab.bankapp.R
import com.abdallahehab.bankapp.databinding.HomeFragmentBinding
import com.abdallahehab.bankapp.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding:HomeFragmentBinding

    private lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /**
         * view binding
         */
        inflater.inflate(R.layout.home_fragment, container, false)
        binding = HomeFragmentBinding.inflate(inflater)



        binding.showAllUsersBtn.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAllUsersFragment())
        }
        binding.showAllTransactions.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToTransactionHistoryFragment())
        }

        return binding.root
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.activity = (activity as MainActivity)

    }

    override fun onResume() {
        super.onResume()
        activity.supportActionBar?.title="home"
    }


}