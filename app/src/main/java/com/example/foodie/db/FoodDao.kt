package com.example.foodie.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.foodie.model.MenuModel
import com.example.foodie.model.UserModel

@Dao
interface FoodDao {
//todo проверить
    @Query("SELECT * FROM food where typesOfFood in ('1')")
    fun getMenu(): List<MenuModel>

//    @Query("SELECT * FROM food where typesOfFood in ('1')")

    @Insert
    fun insertMenu(var1: MenuModel)


}