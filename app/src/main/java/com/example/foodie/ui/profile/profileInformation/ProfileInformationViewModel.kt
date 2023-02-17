package com.example.foodie.ui.profile.profileInformation

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodie.db.DataBase
import com.example.foodie.model.UserModel
import kotlinx.coroutines.launch

class ProfileInformationViewModel(val app: Application): AndroidViewModel(app) {

    val db: DataBase = DataBase.getInstance(app)

    private val _user: MutableLiveData<UserModel> = MutableLiveData()
    val user: LiveData<UserModel> = _user

    init {
        viewModelScope.launch {
            getUser()
        }
    }

    fun getUser(){
        val userId = app.getSharedPreferences("prefs", Context.MODE_PRIVATE)
            .getInt("user_id", -1)
        val user = db.userDao().getUserById(userId)
        _user.postValue(user)
    }

}