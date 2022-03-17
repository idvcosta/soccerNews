package com.ingrid.newssoccer.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingrid.newssoccer.api.ServiceFactoryAPI
import com.ingrid.newssoccer.model.News
import com.ingrid.newssoccer.repositories.NewsRepository
import com.ingrid.newssoccer.usecases.OpenLinkUseCase
import com.ingrid.newssoccer.usecases.ShareUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel(
    private val repository: NewsRepository,
    private val openLinkUserCase: OpenLinkUseCase,
    private val shareUserCase: ShareUserCase
) : ViewModel() {

    private val newsList = MutableLiveData<List<News>>()

    init {
        requestNews()
    }

    fun getNewsList(): LiveData<List<News>> = newsList

    private fun requestNews() {
        viewModelScope.launch(Dispatchers.IO) {
            val service = ServiceFactoryAPI.createNewsService()
            val listNewsCall = service.listNews()

            listNewsCall.enqueue(object : Callback<List<News>> {
                override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                    response.body()?.let { news ->
                        newsList.postValue(news)
                    }
                }

                override fun onFailure(call: Call<List<News>>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })
        }
    }

    fun favoriteNews(news: News) {
        news.isFavorite = !news.isFavorite
        viewModelScope.launch(Dispatchers.IO) {
            repository.addFavorite(news)
            newsList.postValue(newsList.value)
        }
    }

    fun openNewsLink(news: News) {
        openLinkUserCase.execute(news)
    }

    fun shareNews(news: News) {
        shareUserCase.execute(news)
    }
}