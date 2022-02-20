package com.abdallahehab.bankapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface TransactionDao {


    @Query("SELECT * FROM `Transaction`")
    fun getAllTransactions(): LiveData<List<Transaction>
>
    @Insert
    suspend fun insertTransaction(vararg transaction: Transaction)
}