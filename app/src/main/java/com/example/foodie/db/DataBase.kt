package com.example.foodie.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.foodie.model.MutableListConverter
import com.example.foodie.model.MenuModel
import com.example.foodie.model.ShoppingCartModel
import com.example.foodie.model.UserModel

@Database(entities = [MenuModel::class, UserModel::class, ShoppingCartModel::class], version = 1)
@TypeConverters(MutableListConverter::class)
abstract class DataBase : RoomDatabase() {
    abstract fun menuDao(): FoodDao
    abstract fun userDao(): UserDao
    abstract fun shoppingCartDao(): ShoppingCartDao

    companion object {
        @Volatile
        private var INSTANCE: DataBase? = null

        fun getInstance(context: Context): DataBase {
            synchronized(this) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                        context,
                        DataBase::class.java,
                        "main.db"
                    )
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                super.onCreate(db)

                            }
                        })
                        .allowMainThreadQueries()
                        .build()
                }
                return INSTANCE!!
            }
        }


    }
}
