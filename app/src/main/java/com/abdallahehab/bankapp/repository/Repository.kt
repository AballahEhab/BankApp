package com.abdallahehab.bankapp.repository

import com.abdallahehab.bankapp.database.AppDatabase
import com.abdallahehab.bankapp.database.Transaction
import com.abdallahehab.bankapp.database.User
import com.abdallahehab.bankapp.users
import javax.inject.Inject

class Repository @Inject constructor(appDatabase:AppDatabase) {
    private val usersDao = appDatabase.userDao()
    private val transactionsDao = appDatabase.transactionDao()

    suspend fun insertUsers(){
        usersDao.inserAllUsers(users)
    }

    fun getAllUsers() = usersDao.getAllUsers()
    suspend fun updateUser(user:User) = usersDao.update(user)
    fun getAllUserNames() = usersDao.getAllUserNames()
    fun getAllTransactions() = transactionsDao.getAllTransactions()
    suspend fun insertTransaction(transaction: Transaction) = transactionsDao.insertTransaction(transaction)
    suspend fun getUserByEmail(email: String) = usersDao.getUserByEmail(email)
}
