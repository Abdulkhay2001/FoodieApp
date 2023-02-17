package com.example.foodie.ui.signInUp.signIn

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.foodie.db.DataBase
import com.example.foodie.model.UserModel
import kotlinx.coroutines.launch

class SignInViewModel(application: Application) : AndroidViewModel(application) {

    private val db: DataBase = DataBase.getInstance(application)

    private val _allUser: MutableLiveData<List<UserModel>> = MutableLiveData()
    private val allUser: LiveData<List<UserModel>> = _allUser

    private val _showDialog = MutableLiveData<String>()
    val showDialog: LiveData<String> = _showDialog

    private val _open = MutableLiveData<UserModel>()
    val open: LiveData<UserModel> = _open

    init {
        viewModelScope.launch {
            var tmp = db.userDao().getAllUsers()
            if (tmp.isEmpty()){
                db.userDao().insertUser(UserModel("Abdulhay", "abdul", "abdul"))
                tmp = db.userDao().getAllUsers()
            }
            _allUser.postValue(tmp)
        }

    }

    fun send(mail: String, pass: String) {
        var user: UserModel? = null
        allUser.value?.forEach {
            if (it.email == mail && it.password == pass) {
                user = it
            }
        }
        if (user != null) {
            _open.postValue(user!!)
        } else {
            _showDialog.postValue("Invalid username or password")
        }
    }

    fun getAllUser(){
        _allUser.value = db.userDao().getAllUsers()
    }

}