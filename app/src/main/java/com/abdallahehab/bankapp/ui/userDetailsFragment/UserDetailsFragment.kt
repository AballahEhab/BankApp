package com.abdallahehab.bankapp.ui.userDetailsFragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.abdallahehab.bankapp.R
import com.abdallahehab.bankapp.databinding.UserDetailsFragmentBinding
import com.abdallahehab.bankapp.ui.MainActivity
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailsFragment : Fragment() {

    val viewModel: UserDetailsViewModel by viewModels()

    val args: UserDetailsFragmentArgs by navArgs()

    private lateinit var binding: UserDetailsFragmentBinding

    private lateinit var activity: MainActivity

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        inflater.inflate(R.layout.user_details_fragment, container, false)

        binding = UserDetailsFragmentBinding.inflate(inflater)

        viewModel.currentUser.value = args.userData

        binding.viewModel = viewModel

        binding.lifecycleOwner = this

        viewModel.receiverErrorMessage.observe(viewLifecycleOwner, Observer {
            binding.allUsersSpiner.error = it
        })
        viewModel.transferredAmountErrorMessage.observe(viewLifecycleOwner, Observer {
            binding.transfereAmountInLayout.error = it

        })

        viewModel.successfulMessageVisiblity.observe(viewLifecycleOwner) {
            if (it) {
                Snackbar.make(binding.root, "Transaction done successfully", Snackbar.LENGTH_SHORT).show()

                viewModel.disableSuccessfulMessageVisiblity()
            }
        }

        viewModel.listOfUserNames.observe(viewLifecycleOwner, Observer{
             val adapter = ArrayAdapter(requireContext(), R.layout.user_name_list_item, it)
            (binding.allUsersSpiner.editText as? AutoCompleteTextView)?.setAdapter(adapter)

        })


        return binding.root
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        this.activity = (activity as MainActivity)

    }

    override fun onResume() {
        super.onResume()
        activity.supportActionBar?.title="profile"
    }

}