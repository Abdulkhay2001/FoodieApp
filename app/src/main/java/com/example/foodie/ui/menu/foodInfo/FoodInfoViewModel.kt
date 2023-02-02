package com.example.foodie.ui.menu.foodInfo

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodie.db.DataBase
import com.example.foodie.model.MenuModel
import com.example.foodie.model.ShoppingCartModel
import kotlinx.coroutines.launch

class FoodInfoViewModel(application: Application) : AndroidViewModel(application) {

    val db: DataBase = DataBase.getInstance(application)

    private val userId = application.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        .getInt("user_id", -1)

    private val _allMenu: MutableLiveData<List<MenuModel>> = MutableLiveData()
    val allMenu: LiveData<List<MenuModel>> = _allMenu

    private val _menu = MutableLiveData<MenuModel>()
    val menu: LiveData<MenuModel> = _menu

    private val _shoppingCart: MutableLiveData<List<ShoppingCartModel>> = MutableLiveData()
    val shoppingCart: LiveData<List<ShoppingCartModel>> = _shoppingCart

    init {
        viewModelScope.launch {

            val tmp = db.menuDao().getAllMenu()
            _allMenu.postValue(tmp)

            _shoppingCart.postValue(db.shoppingCartDao().getShoppingCart(userId))
        }
    }

    fun insert(userId: Int, menuId: Int, count: Int) {
        viewModelScope.launch {
            db.shoppingCartDao().insertShoppingCart(ShoppingCartModel(userId, menuId, count))
            _shoppingCart.postValue(db.shoppingCartDao().getShoppingCart(userId))
        }
    }

    fun update(shoppingCartModel: ShoppingCartModel){
        db.shoppingCartDao().update(shoppingCartModel)
    }


    fun initArgs(idFood: Int) {
        viewModelScope.launch {
            _menu.value = db.menuDao().getMenu(idFood)
        }
    }


}