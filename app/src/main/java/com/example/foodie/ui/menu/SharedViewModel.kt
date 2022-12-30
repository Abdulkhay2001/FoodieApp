package com.example.foodie.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodie.model.Event

class SharedViewModel() : ViewModel() {
    private val _onFavoriteUpdate = MutableLiveData<Event<Unit>>()
    val onFavoriteUpdate: LiveData<Event<Unit>> = _onFavoriteUpdate

    fun onFavoriteBtnClick() {
        _onFavoriteUpdate.postValue(Event(Unit))
    }
}