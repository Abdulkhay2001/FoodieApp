package com.example.foodie.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.foodie.model.MenuModel

@Dao
interface FoodDao {
    @Query("SELECT * FROM food where typesOfFood in ('1')")
    fun getMealsMenu(): List<MenuModel>

    @Query("SELECT * FROM food where typesOfFood in ('2')")
    fun getSidesMenu(): List<MenuModel>

    @Query("SELECT * FROM food where typesOfFood in ('3')")
    fun getSnacksMenu(): List<MenuModel>

    @Query("SELECT * FROM food where id = :id")
    fun getMenu(id: Int): MenuModel

    @Query("SELECT * FROM food")
    fun getAllMenu(): List<MenuModel>

    @Insert
    fun insertMenu(var1: MenuModel)


}