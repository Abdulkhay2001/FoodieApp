package com.example.foodie.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food")
class MenuModel(
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val price: Int,
    @ColumnInfo
    val image: Int,
    @ColumnInfo
    val typesOfFood: String,
    @ColumnInfo
    val desc: String
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}