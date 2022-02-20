package com.abdallahehab.bankapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE

@Dao
interface UserDao {
    @Query("SELECT * FROM `user`")
      fun getAllUsers(): LiveData<List<User>>

    @Query("SELECT userEmail FROM user")
     fun getAllUserNames(): LiveData<List<String>>

     @Query("SELECT * FROM user WHERE userEmail=:email")
     suspend fun getUserByEmail(email:String): User

    @Insert
     suspend fun inserAllUsers(users:List<User>)

    @Update
     suspend fun update( user: User)


}