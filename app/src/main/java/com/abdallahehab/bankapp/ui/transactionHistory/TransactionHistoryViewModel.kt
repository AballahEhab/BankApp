package com.abdallahehab.bankapp.ui.transactionHistory

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.abdallahehab.bankapp.database.dataBaseMapper
import com.abdallahehab.bankapp.domain.TransactionData
import com.abdallahehab.bankapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TransactionHistoryViewModel @Inject constructor(repo:Repository): ViewModel() {
    var transactionsList =Transformations.map( repo.getAllTransactions()){ transactionsList ->
        transactionsList.map { dataBaseMapper.transactionToTransactionData(it) }
    }


}