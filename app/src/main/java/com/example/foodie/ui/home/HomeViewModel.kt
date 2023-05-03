package com.example.foodie.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.example.foodie.db.DataBase
import com.example.foodie.model.MenuModel
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val db: DataBase = DataBase.getInstance(application)

    private val _allMenu: MutableLiveData<List<MenuModel>> = MutableLiveData()
    val allMenu: LiveData<List<MenuModel>> = _allMenu

    init {
        viewModelScope.launch {

            val tmp = db.menuDao().getAllMenu()
            _allMenu.postValue(tmp)

        }
    }

}