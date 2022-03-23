package com.ingrid.newssoccer.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.ingrid.newssoccer.data.local.SoccerNewsDatabase
import com.ingrid.newssoccer.data.remote.ServiceFactoryAPI
import com.ingrid.newssoccer.model.News
import retrofit2.Callback

class NewsRepository(context: Context) {
    // Chamada Retrofit
    private val service = ServiceFactoryAPI.createNewsService()

    // Chamada Room
    private val db = Room.databaseBuilder(
        context,
        SoccerNewsDatabase::class.java,
        "soccerNews"
    )
        .build()


    fun listNews(callback: Callback<List<News>>) {
        service.listNews().enqueue(callback)
    }

    fun save(news: News) {
        db.newsDao().save(news)
    }

    fun allFavorites(): LiveData<List<News>> {
        return db.newsDao().loadFavoritesNews()
    }

    fun allNews(): LiveData<List<News>> =
        db.newsDao().allNews()
}
