package com.ingrid.newssoccer.api

import com.ingrid.newssoccer.model.News
import retrofit2.Call
import retrofit2.http.GET

interface SoccerNewsService {

    @GET("news.json")
    fun listNews(): Call<List<News>>
}