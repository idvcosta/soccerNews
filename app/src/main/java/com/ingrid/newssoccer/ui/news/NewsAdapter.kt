package com.ingrid.newssoccer.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ingrid.newssoccer.R
import com.ingrid.newssoccer.databinding.NewsItemBinding
import com.ingrid.newssoccer.model.News
import com.squareup.picasso.Picasso
import java.util.function.Consumer

class NewsAdapter(
    private val favoriteCallback: Consumer<News>,
    private val openNewsCallback: Consumer<News>,
    private val shareNewsCallback: Consumer<News>
) :
    RecyclerView.Adapter<NewsAdapter.NewsHolder>() {

    private var news: List<News>? = null

    class NewsHolder(private val newsRow: NewsItemBinding) :
        RecyclerView.ViewHolder(newsRow.root) {
        fun bind(news: News) {
            newsRow.root.tag = news

            newsRow.tvTitleNews.text = news.title
            newsRow.tvSubtitleNews.text = news.description
            Picasso.get()
                .load(news.image)
                .into(newsRow.ivNews)

            val favoriteIconColor = if (news.isFavorite){
                R.color.favorite_active
            }else{
                R.color.favorite_inactive
            }
            val color = newsRow.root.context.getColor(favoriteIconColor)
            newsRow.ivFavorite.setColorFilter(color)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val newsRow = NewsItemBinding.inflate(inflater, parent, false)

        newsRow.btOpenLink.setOnClickListener {
            val news = newsRow.root.tag as News
            openNewsCallback.accept(news)
        }
        newsRow.ivShare.setOnClickListener {
            val news = newsRow.root.tag as News
            shareNewsCallback.accept(news)
        }

        newsRow.ivFavorite.setOnClickListener {
            val news = newsRow.root.tag as News
            favoriteCallback.accept(news)
        }

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
