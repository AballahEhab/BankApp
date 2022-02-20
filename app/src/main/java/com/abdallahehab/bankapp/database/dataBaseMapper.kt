package com.abdallahehab.bankapp.database

import com.abdallahehab.bankapp.domain.TransactionData
import com.abdallahehab.bankapp.domain.UserData

class dataBaseMapper {

    companion object {
        fun userToUserData(user: User) = UserData(
            user.userName, user.userEmail, user.balance
        )
        fun transactionToTransactionData(transaction: Transaction) = TransactionData(
            transaction.senderEmail,transaction.receiverEmail,transaction.amount
        )
    }
}