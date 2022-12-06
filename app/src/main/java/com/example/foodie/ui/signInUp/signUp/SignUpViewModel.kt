package com.example.foodie.ui.signInUp.signUp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodie.db.DataBase
import com.example.foodie.model.UserModel
import kotlinx.coroutines.launch

class SignUpViewModel(application: Application) : AndroidViewModel(application) {

    private val db: DataBase = DataBase.getInstance(application)

    private val _allUser: MutableLiveData<List<UserModel>> = MutableLiveData()
    private val allUser: LiveData<List<UserModel>> = _allUser

    private val _showDialog = MutableLiveData<String>()
    val showDialog: LiveData<String> = _showDialog

    fun send(name: String, mail: String, pass: String) {
        viewModelScope.launch {
            db.userDao().insertUser(UserModel(name, mail, pass, false))
        }
    }

}