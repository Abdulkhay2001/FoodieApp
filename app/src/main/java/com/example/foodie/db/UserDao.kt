package com.example.foodie.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.foodie.model.UserModel

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<UserModel>

    @Insert
    fun insertUser(var2: UserModel)

}