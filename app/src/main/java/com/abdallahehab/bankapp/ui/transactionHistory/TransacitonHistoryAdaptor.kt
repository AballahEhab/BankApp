package com.abdallahehab.bankapp.ui.transactionHistory

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abdallahehab.bankapp.databinding.TransactionRecordDetailsBinding
import com.abdallahehab.bankapp.domain.TransactionData
import com.abdallahehab.bankapp.util.intText


class TransactionHistoryAdaptor : RecyclerView.Adapter<TransactionHistoryAdaptor.TransactionViewHolder>() {
    var dataList = listOf<TransactionData>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransactionViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TransactionRecordDetailsBinding.inflate(inflater, parent, false)
        return TransactionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TransactionViewHolder, position: Int) {
        holder.bind(dataList[position])

    }

    override fun getItemCount() = dataList.size

    class TransactionViewHolder(var binding: TransactionRecordDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(dataItem: TransactionData) {
            binding.txtSenderName.text =dataItem.sender
            binding.txtReceiverName.text =dataItem.receiver
            binding.txtAmount.intText(dataItem.amount)
        }
    }
}


