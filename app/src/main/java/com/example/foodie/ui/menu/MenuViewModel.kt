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
            var tmp = db.menuDao().getMealsMenu()
            if (tmp.isEmpty()) {
                db.menuDao().insertMenu(MenuModel("meat", 60, false, R.drawable.food_image, "1", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("first", 20, false, R.drawable.food_image, "1", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("second", 40, false, R.drawable.food_image, "1", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("disert", 55, false, R.drawable.food_image, "2", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("fish", 33, false, R.drawable.food_image, "2", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("ice cream", 22, false, R.drawable.food_image, "3", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some food", 56, false, R.drawable.food_image, "3", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                tmp = db.menuDao().getMealsMenu()
            }
            _allMenu.postValue(db.menuDao().getMealsMenu())
        }
    }

    fun initArgs(category: Int) {
        when(category) {
            1 -> {
                _allMenu.postValue(db.menuDao().getMealsMenu())
            }
            2 -> {
                _allMenu.postValue(db.menuDao().getSidesMenu())
            }
            3-> {
                _allMenu.postValue(db.menuDao().getSnacksMenu())
            }
        }
    }
}