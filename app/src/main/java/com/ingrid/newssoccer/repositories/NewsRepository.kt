package com.ingrid.newssoccer.repositories

import android.content.Context
import androidx.room.Room
import com.ingrid.newssoccer.model.News

class NewsRepository(context: Context) {
    private val db = Room.databaseBuilder(
        context,
        SoccerNewsDatabase::class.java,
        "soccerNews"
    ).build()

    fun addFavorite(news: News) {
        db.newsDao().insert(news)
    }
}
