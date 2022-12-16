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
                db.menuDao().insertMenu(MenuModel("some meals 1", 60, false, R.drawable.food_image, "1", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some meals 2", 20, false, R.drawable.food_image, "1", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some meals 3", 40, false, R.drawable.food_image, "1", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some meals 4", 32, false, R.drawable.food_image, "1", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some meals 5", 31, false, R.drawable.food_image, "1", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some sides 1", 55, false, R.drawable.food_image, "2", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some sides 2", 33, false, R.drawable.food_image, "2", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some sides 3", 38, false, R.drawable.food_image, "2", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some sides 4", 43, false, R.drawable.food_image, "2", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some sides 5", 53, false, R.drawable.food_image, "2", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some sides 6", 13, false, R.drawable.food_image, "2", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some snacks 1", 22, false, R.drawable.food_image, "3", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some snacks 2", 56, false, R.drawable.food_image, "3", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some snacks 2", 42, false, R.drawable.food_image, "3", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some snacks 3", 65, false, R.drawable.food_image, "3", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some snacks 4", 13, false, R.drawable.food_image, "3", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some snacks 5", 27, false, R.drawable.food_image, "3", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some snacks 6", 39, false, R.drawable.food_image, "3", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some snacks 7", 52, false, R.drawable.food_image, "3", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
                db.menuDao().insertMenu(MenuModel("some snacks 8", 35, false, R.drawable.food_image, "3", "Some description, Some description, Some description, Some description, Some description, Some description, Some description"))
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