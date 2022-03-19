package com.ingrid.newssoccer.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingrid.newssoccer.model.News
import com.ingrid.newssoccer.repositories.NewsRepository
import com.ingrid.newssoccer.usecases.OpenLinkUseCase
import com.ingrid.newssoccer.usecases.ShareUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class FavoritesViewModel(
    private val repository: NewsRepository,
    private val openLinkUserCase: OpenLinkUseCase,
    private val shareUserCase: ShareUserCase
) : ViewModel() {

    private lateinit var favoritesList : LiveData<List<News>>

    init {
        loadFavorites()
    }

    fun getFavoriteList(): LiveData<List<News>> = favoritesList

    private fun loadFavorites() {
        runBlocking {
            withContext(Dispatchers.IO){
                favoritesList = repository.allFavorites()
            }
        }
    }

    fun removeFavoriteNews(news: News) {
        news.isFavorite = false
        viewModelScope.launch(Dispatchers.IO) {
            repository.save(news)
        }
    }

    fun openNewsLink(news: News) {
        openLinkUserCase.execute(news)
    }

    fun shareNews(news: News) {
        shareUserCase.execute(news)
    }
}