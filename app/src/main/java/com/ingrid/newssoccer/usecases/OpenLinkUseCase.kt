package com.ingrid.newssoccer.usecases

import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.net.Uri
import com.ingrid.newssoccer.model.News

class OpenLinkUseCase(private val context: Context) {
    fun execute(news: News) {
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = Uri.parse(news.link)
            flags = FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }

}
