package com.ingrid.newssoccer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ingrid.newssoccer.model.News

@Database(entities = [News::class], version = 1)
abstract class SoccerNewsDatabase : RoomDatabase() {
    abstract fun newsDao(): NewsDAO
}