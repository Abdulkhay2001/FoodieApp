package com.example.foodie.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MutableListConverter {
    @TypeConverter
    fun toString(value: MutableList<Int>?): String {

        return Gson().toJson(value)

    }

    @TypeConverter
    fun toList(value: String?): MutableList<Int> {
        return try {
            Gson().fromJson<MutableList<Int>>(value) //using extension function
        } catch (e: Exception) {
            arrayListOf()
        }
    }

}

inline fun <reified T> Gson.fromJson(json: String?) =
    fromJson<T>(json, object : TypeToken<T>() {}.type)