package com.example.foodie.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shoppingCart")
class ShoppingCartModel(
    @ColumnInfo
    val userId: Int,
    @ColumnInfo
    val menuId:Int,
    @ColumnInfo
    var count: Int
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}