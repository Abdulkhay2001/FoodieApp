package com.example.foodie.ui.home

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.foodie.R
import com.example.foodie.db.DataBase
import com.example.foodie.model.MenuModel
import com.example.foodie.model.ShoppingCartModel
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    val db: DataBase = DataBase.getInstance(application)

    private val userId = application.getSharedPreferences("prefs", Context.MODE_PRIVATE)
        .getInt("user_id", -1)

    private val _allMenu: MutableLiveData<List<MenuModel>> = MutableLiveData()
    val allMenu: LiveData<List<MenuModel>> = _allMenu

    private val _shoppingCart: MutableLiveData<List<ShoppingCartModel>> = MutableLiveData()
    val shoppingCart: LiveData<List<ShoppingCartModel>> = _shoppingCart

    init {
        viewModelScope.launch {
            val tmp = db.menuDao().getAllMenu()
            if (tmp.isEmpty()) {
                db.menuDao().insertMenu(
                    MenuModel(
                        "some meals 1",
                        60,
                        R.drawable.food_image,
                        "1",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some meals 2",
                        20,
                        R.drawable.food_image,
                        "1",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some meals 3",
                        40,
                        R.drawable.food_image,
                        "1",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some meals 4",
                        32,
                        R.drawable.food_image,
                        "1",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some meals 5",
                        31,
                        R.drawable.food_image,
                        "1",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some sides 1",
                        55,
                        R.drawable.food_image,
                        "2",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some sides 2",
                        33,
                        R.drawable.food_image,
                        "2",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some sides 3",
                        38,
                        R.drawable.food_image,
                        "2",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some sides 4",
                        43,
                        R.drawable.food_image,
                        "2",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some sides 5",
                        53,
                        R.drawable.food_image,
                        "2",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some sides 6",
                        13,
                        R.drawable.food_image,
                        "2",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some snacks 1",
                        22,
                        R.drawable.food_image,
                        "3",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some snacks 2",
                        56,
                        R.drawable.food_image,
                        "3",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some snacks 3",
                        42,
                        R.drawable.food_image,
                        "3",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some snacks 4",
                        65,
                        R.drawable.food_image,
                        "3",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some snacks 5",
                        13,
                        R.drawable.food_image,
                        "3",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some snacks 6",
                        27,
                        R.drawable.food_image,
                        "3",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some snacks 7",
                        39,
                        R.drawable.food_image,
                        "3",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some snacks 8",
                        52,
                        R.drawable.food_image,
                        "3",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
                db.menuDao().insertMenu(
                    MenuModel(
                        "some snacks 9",
                        35,
                        R.drawable.food_image,
                        "3",
                        "Some description, Some description, Some description, Some description, Some description, Some description, Some description"
                    )
                )
            }

            _allMenu.postValue(db.menuDao().getAllMenu())

            _shoppingCart.postValue(db.shoppingCartDao().getShoppingCart(userId))
        }
    }

    fun update(shoppingCartModel: ShoppingCartModel){
        db.shoppingCartDao().update(shoppingCartModel)
    }

    fun insert(userId: Int, menuId: Int, count: Int) {
        viewModelScope.launch {
            db.shoppingCartDao().insertShoppingCart(ShoppingCartModel(userId, menuId, count))
            _shoppingCart.postValue(db.shoppingCartDao().getShoppingCart(userId))
        }
    }

}