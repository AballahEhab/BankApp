package com.abdallahehab.bankapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User(
    @PrimaryKey val userName:String,
    val userEmail:String,
    val balance:Int

)

@Entity
data class Transaction(
    val senderEmail:String,
    val receiverEmail:String,
    val amount:Int

){
    @PrimaryKey(autoGenerate = true)  var id:Int =0
}

