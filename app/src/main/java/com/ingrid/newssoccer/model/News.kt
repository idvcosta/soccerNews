package com.ingrid.newssoccer.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class News(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val link: String,
    var isFavorite: Boolean
)