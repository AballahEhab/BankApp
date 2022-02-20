package com.abdallahehab.bankapp.ui.allUsers

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.abdallahehab.bankapp.databinding.UserRecordDetailsViewBinding
import com.abdallahehab.bankapp.domain.UserData
import com.abdallahehab.bankapp.util.intText


class AllUsersAdapter : RecyclerView.Adapter<AllUsersAdapter.ListItemViewHolder>() {
    var dataList = listOf<UserData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = UserRecordDetailsViewBinding.inflate(inflater, parent, false)
        return ListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(dataList[position])

    }

    override fun getItemCount() = dataList.size

    class ListItemViewHolder(var binding: UserRecordDetailsViewBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(dataItem: UserData) {
            binding.userName.text = dataItem.userName
            binding.userEmail.text = dataItem.email
            binding.detailedUserBalance.intText(dataItem.balance)
            itemView.setOnClickListener {
                it.findFragment<Fragment>().findNavController()
                    .navigate(AllUsersFragmentDirections.actionAllUsersFragmentToCustomerDetailsFragment(dataItem))
            }
        }
    }
}


