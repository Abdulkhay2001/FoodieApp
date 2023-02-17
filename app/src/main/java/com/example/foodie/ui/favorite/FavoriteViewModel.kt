package com.example.foodie.ui.favorite

import android.app.Application
import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodie.db.DataBase
import com.example.foodie.model.MenuModel
import com.example.foodie.model.UserModel
import kotlinx.coroutines.launch

class FavoriteViewModel(val app: Application) : AndroidViewModel(app) {

    val db: DataBase = DataBase.getInstance(app)

    private val _favorite: MutableLiveData<List<MenuModel>> = MutableLiveData()
    val favorite: LiveData<List<MenuModel>> = _favorite

    init {
        viewModelScope.launch {
            getFavorite()
        }
    }

    fun getFavorite() {
        val userId = app.getSharedPreferences("prefs", Context.MODE_PRIVATE)
            .getInt("user_id", -1)
        val user = db.userDao().getUserById(userId)
        val list = mutableListOf<MenuModel>()

        user.favoriteList.forEach {
            val menuModel = db.menuDao().getMenu(it)
            list.add(menuModel)
        }
        _favorite.postValue(list)
    }

    fun updateFavorites(userModel: UserModel) {
        db.userDao().update(userModel)
    }

}