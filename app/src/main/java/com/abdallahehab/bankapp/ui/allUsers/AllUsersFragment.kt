package com.abdallahehab.bankapp.ui.allUsers

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.abdallahehab.bankapp.R
import com.abdallahehab.bankapp.databinding.AllUsersFragmentBinding
import com.abdallahehab.bankapp.ui.MainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AllUsersFragment : Fragment() {

    val viewModel: AllUsersViewModel by viewModels()

    private lateinit var binding:AllUsersFragmentBinding

    private lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /**
         *data binding
         */
        inflater.inflate(R.layout.all_users_fragment, container, false)
        binding = AllUsersFragmentBinding.inflate(inflater)

        (activity?.parent)?.title = " Bank app home screen"



        constructRecyclerView()


        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.updateUsers()
    }

    fun constructRecyclerView(){
        val adapter = AllUsersAdapter()
        viewModel.usersDataList.observe(viewLifecycleOwner,Observer {
            adapter.dataList = it
            binding.allUsersRecyclerView.adapter = adapter
        })
        val layoutManager = LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)

        binding.allUsersRecyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                layoutManager.orientation
            )
        )

        binding.allUsersRecyclerView.layoutManager = layoutManager
        }
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.activity = (activity as MainActivity)

    }

    override fun onResume() {
        super.onResume()
        activity.supportActionBar?.title="All users"
    }

}