package com.ingrid.newssoccer.api

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceFactoryAPI {

    private const val BASE_URL: String = "https://idvcosta.github.io/soccerNewsAPI/"

    fun createNewsService(): SoccerNewsService {

        val gson = GsonBuilder()
            .setLenient()
            .create()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()

        return retrofit.create(SoccerNewsService::class.java)
    }

}