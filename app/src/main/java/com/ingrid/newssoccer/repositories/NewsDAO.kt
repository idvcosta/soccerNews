package com.ingrid.newssoccer.repositories

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ingrid.newssoccer.model.News

@Dao
interface NewsDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(news: News)

    @Query("SELECT * FROM news WHERE isFavorite = 1")
    fun loadFavoritesNews(): List<News>
}