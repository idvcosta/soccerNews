package com.ingrid.newssoccer.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ingrid.newssoccer.News
import com.ingrid.newssoccer.databinding.NewsItemBinding

class NewsAdapter : RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    class NewsHolder(private val newsRow: NewsItemBinding) :
        RecyclerView.ViewHolder(newsRow.root) {
        fun bind(news: News) {
            newsRow.root.tag = news

            newsRow.tvTitleNews.text = news.title
            newsRow.tvSubtitleNews.text = news.description
        }
    }

    var news: List<News>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val newsRow = NewsItemBinding.inflate(inflater, parent, false)

        return NewsHolder(newsRow)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        news?.let {
            val news = it[position]
            holder.bind(news)
        }
    }

    override fun getItemCount(): Int = news?.size ?: 0

    fun updateNews(news: List<News>) {
        this.news = news
        notifyDataSetChanged()
    }

}
