package com.ingrid.newssoccer.ui.news

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ingrid.newssoccer.data.NewsRepository
import com.ingrid.newssoccer.model.News
import com.ingrid.newssoccer.ui.State
import com.ingrid.newssoccer.usecases.OpenLinkUseCase
import com.ingrid.newssoccer.usecases.ShareUserCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel(
    private val repository: NewsRepository,
    private val openLinkUserCase: OpenLinkUseCase,
    private val shareUserCase: ShareUserCase
) : ViewModel() {

    private lateinit var newsList: LiveData<List<News>>
    private val state = MutableLiveData(State.PROGRESS)

    init {
        loadNews()
        requestNews()
    }

    fun getNewsList() = newsList
    fun getStatus(): LiveData<State> = state

    private fun loadNews() {
        runBlocking {
            newsList = repository.allNews()
        }
    }

    private fun requestNews() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.listNews(object : Callback<List<News>> {
                override fun onResponse(call: Call<List<News>>, response: Response<List<News>>) {
                    response.body()?.let { news ->
                        viewModelScope.launch(Dispatchers.IO) {
                            news.forEach(repository::save)
                        }
                        state.postValue(State.DATA_LOADED)
                    }
                }

                override fun onFailure(call: Call<List<News>>, t: Throwable) {
                    Log.e(this@NewsViewModel.toString(), "Error loading News", t)
                    state.postValue(State.ERROR)
                }
            })
        }
    }

    fun favoriteNews(news: News) {
        news.isFavorite = !news.isFavorite
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