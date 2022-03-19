package com.ingrid.newssoccer.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.ingrid.newssoccer.model.News

class NewsRepository(context: Context) {
    private val db = Room.databaseBuilder(
        context,
        SoccerNewsDatabase::class.java,
        "soccerNews"
    )
        .build()

    fun save(news: News) {
        db.newsDao().save(news)
    }

    fun allFavorites(): LiveData<List<News>> {
        return db.newsDao().loadFavoritesNews()
    }

    fun allNews(): LiveData<List<News>> =
        db.newsDao().allNews()
}
