package com.example.foodie.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.foodie.model.ShoppingCartModel

@Dao
interface ShoppingCartDao {

    @Query("SELECT * FROM shoppingCart WHERE userId = :id")
    fun getShoppingCart(id: Int): List<ShoppingCartModel>

    @Insert
    fun insertShoppingCart(var1: ShoppingCartModel)

    @Query("SELECT * FROM shoppingCart WHERE menuId = :menuId AND userId = :userId")
    fun checkShoppingCart(menuId: Int, userId: Int): ShoppingCartModel?

    @Query("DELETE FROM shoppingCart")
    fun deleteAllShoppingCart()

    @Update
    fun update(f: ShoppingCartModel)

    @Delete
    fun delete(d: ShoppingCartModel)

}