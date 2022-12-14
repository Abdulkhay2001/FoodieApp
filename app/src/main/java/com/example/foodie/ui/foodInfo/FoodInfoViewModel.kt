package com.example.foodie.ui.foodInfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodie.db.DataBase
import com.example.foodie.model.MenuModel
import kotlinx.coroutines.launch

class FoodInfoViewModel(application: Application): AndroidViewModel(application) {

    val db: DataBase = DataBase.getInstance(application)

    private val _allMenu: MutableLiveData<List<MenuModel>> = MutableLiveData()
    val allMenu: LiveData<List<MenuModel>> = _allMenu

    init {
        viewModelScope.launch {
            val tmp = db.menuDao().getAllMenu()
            _allMenu.postValue(tmp)
        }
    }

    fun getMenu(tmp: Int){
        _allMenu.value = listOf(db.menuDao().getMenu(tmp))
    }


}