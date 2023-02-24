package com.example.foodie.ui.profile.settings.password

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.example.foodie.db.DataBase
import com.example.foodie.model.ShoppingCartModel
import com.example.foodie.model.UserModel
import kotlinx.coroutines.launch

class ChPasswordViewModel(val app: Application): AndroidViewModel(app) {

    val db: DataBase = DataBase.getInstance(app)

    private val _user: MutableLiveData<UserModel> = MutableLiveData()
    val user: LiveData<UserModel> = _user

    init {
        viewModelScope.launch {
            getUser()
        }
    }

    private fun getUser(){
        val userId = app.getSharedPreferences("prefs", Context.MODE_PRIVATE)
            .getInt("user_id", -1)
        val user = db.userDao().getUserById(userId)
        _user.postValue(user)
    }

    fun update(userModel: UserModel) {
        db.userDao().update(userModel)
    }

}