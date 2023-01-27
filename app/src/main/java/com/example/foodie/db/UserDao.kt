package com.example.foodie.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.foodie.model.UserModel

@Dao
interface UserDao {

    @Query("SELECT * FROM user")
    fun getAllUsers(): List<UserModel>

    @Query("SELECT * FROM user WHERE id=:id")
    fun getUserById(id: Int): UserModel

    @Insert
    fun insertUser(var2: UserModel)

    @Update
    fun update(f: UserModel)

}