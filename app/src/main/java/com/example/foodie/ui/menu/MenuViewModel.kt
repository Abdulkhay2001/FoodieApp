package com.example.foodie.ui.menu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodie.R
import com.example.foodie.db.DataBase
import com.example.foodie.model.MenuModel
import com.example.foodie.model.ShoppingCartModel
import com.example.foodie.model.UserModel
import kotlinx.coroutines.launch

class MenuViewModel(application: Application) : AndroidViewModel(application) {

    val db: DataBase = DataBase.getInstance(application)

    private val _allMenu: MutableLiveData<List<MenuModel>> = MutableLiveData()
    val allMenu: LiveData<List<MenuModel>> = _allMenu

    init {
        viewModelScope.launch {

            _allMenu.postValue(db.menuDao().getMealsMenu())
        }
    }

    fun initArgs(category: Int) {
        when (category) {
            1 -> {
                _allMenu.postValue(db.menuDao().getMealsMenu())
            }
            2 -> {
                _allMenu.postValue(db.menuDao().getSidesMenu())
            }
            3 -> {
                _allMenu.postValue(db.menuDao().getSnacksMenu())
            }
        }
    }

    fun updateFavorites(userModel: UserModel) {
        db.userDao().update(userModel)
    }

    fun update(menuModel: MenuModel, category: Int) {

        db.menuDao().update(menuModel)
        initArgs(category)

    }



}