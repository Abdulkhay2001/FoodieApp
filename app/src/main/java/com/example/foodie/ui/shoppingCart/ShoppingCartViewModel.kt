package com.example.foodie.ui.shoppingCart

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.foodie.db.DataBase
import com.example.foodie.model.ShoppingCartModel

class ShoppingCartViewModel(app: Application) : AndroidViewModel(app) {

    val db: DataBase = DataBase.getInstance(app)

    private val _shoppingCart: MutableLiveData<List<ShoppingCartModel>> = MutableLiveData()
    val shoppingCart: LiveData<List<ShoppingCartModel>> = _shoppingCart
    val userId = app.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        .getInt("user_id", -1)

    init {
        getShoppingCart(userId)
    }

    private fun getShoppingCart(userId: Int) {
        _shoppingCart.postValue(db.shoppingCartDao().getShoppingCart(userId))
    }

    fun update(shoppingCartModel: ShoppingCartModel) {
        db.shoppingCartDao().update(shoppingCartModel)
    }

    fun delete(shoppingCartModel: ShoppingCartModel, userId: Int) {
        db.shoppingCartDao().delete(shoppingCartModel)
        _shoppingCart.postValue(db.shoppingCartDao().getShoppingCart(userId))

    }

    fun deleteAll(){
        db.shoppingCartDao().deleteAllShoppingCart()
    }

    fun price(): Int {
        var result =0
        shoppingCart.value!!.forEach {
            result += db.menuDao().getMenu(it.menuId).price * it.count
        }
        return result
    }


}