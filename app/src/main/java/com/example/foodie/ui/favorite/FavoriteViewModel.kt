package com.example.foodie.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodie.db.DataBase
import com.example.foodie.model.MenuModel
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    val db: DataBase = DataBase.getInstance(application)

    private val _favorite: MutableLiveData<List<MenuModel>> = MutableLiveData()
    val favorite: LiveData<List<MenuModel>> = _favorite

    init {
        viewModelScope.launch {
            val tmp = db.menuDao().getFavorites()
            _favorite.postValue(tmp)
        }
    }

    fun update(menuModel: MenuModel){
        db.menuDao().update(menuModel)
        getFavorite()
    }

    fun getFavorite(){
        _favorite.value = db.menuDao().getFavorites()
    }

}