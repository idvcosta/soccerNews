package com.ingrid.newssoccer.usecases

import android.content.Context
import android.content.Intent
import com.ingrid.newssoccer.model.News

class ShareUserCase(private val context: Context) {
    fun execute(news: News) {
        val intent = Intent(Intent.ACTION_SEND).apply {
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, news.link)
        }

        val intentShare =
            Intent.createChooser(intent, "Share via").setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

        context.startActivity(intentShare)
    }

}
