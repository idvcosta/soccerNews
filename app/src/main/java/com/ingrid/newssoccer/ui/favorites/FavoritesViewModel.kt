package com.ingrid.newssoccer.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoritesViewModel : ViewModel() {

    private val favorite = MutableLiveData<String>("This is favorites Fragment")

    fun getFavorite(): LiveData<String> = favorite
}