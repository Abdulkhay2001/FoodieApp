package com.example.foodie.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
class UserModel(
    @ColumnInfo
    val name: String,
    @ColumnInfo
    val email: String,
    @ColumnInfo
    val password: String,
    @ColumnInfo
    val favoriteList: MutableList<Int> = mutableListOf()
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
