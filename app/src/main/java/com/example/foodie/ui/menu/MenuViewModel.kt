package com.example.foodie.ui.menu

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodie.R
import com.example.foodie.db.DataBase
import com.example.foodie.model.MenuModel
import kotlinx.coroutines.launch

class MenuViewModel(application: Application) : AndroidViewModel(application) {

    val db: DataBase = DataBase.getInstance(application)

    private val _allMenu: MutableLiveData<List<MenuModel>> = MutableLiveData()
    val allMenu: LiveData<List<MenuModel>> = _allMenu
    init {
        viewModelScope.launch {
            var tmp = db.menuDao().getMenu()
            if (tmp.isEmpty()) {
                db.menuDao().insertMenu(MenuModel("meat", "60 somon", false, R.drawable.food_image, "1"))
                db.menuDao().insertMenu(MenuModel("first", "20 somon", false, R.drawable.food_image, "1"))
                db.menuDao().insertMenu(MenuModel("second", "40 somon", false, R.drawable.food_image, "1"))
                db.menuDao().insertMenu(MenuModel("disert", "55 somon", false, R.drawable.food_image, ""))
                db.menuDao().insertMenu(MenuModel("fish", "30 somon", false, R.drawable.food_image, ""))
                db.menuDao().insertMenu(MenuModel("ice cream", "22 somon", false, R.drawable.food_image, ""))
                tmp = db.menuDao().getMenu()
            }
            _allMenu.postValue(tmp)
        }
    }




}