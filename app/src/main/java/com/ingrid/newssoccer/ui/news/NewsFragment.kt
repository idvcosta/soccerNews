package com.ingrid.newssoccer.ui.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ingrid.newssoccer.databinding.FragmentNewsBinding
import com.ingrid.newssoccer.model.News
import com.ingrid.newssoccer.ui.State
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding
    private val viewModel by viewModel<NewsViewModel>()
    private val newsAdapter =
        NewsAdapter(::onFavoriteClicked, ::onOpenNewsClicked, ::onShareNewsClicked)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(inflater, container, false)
        binding.rvNews.adapter = newsAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getStatus().observe(requireActivity()) {
            when (it) {
                State.PROGRESS -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.tvError.visibility = View.GONE
                }
                State.DATA_LOADED -> {
                    binding.progress.visibility = View.GONE
                    binding.tvError.visibility = View.GONE
                }
                State.ERROR -> {
                    binding.progress.visibility = View.GONE
                    binding.tvError.visibility = View.VISIBLE
                }
            }
        }

        viewModel.getNewsList().observe(requireActivity(), ::updateNews)
    }

    private fun updateNews(news: List<News>) {
        newsAdapter.updateNews(news)
    }

    private fun onFavoriteClicked(news: News) {
        viewModel.favoriteNews(news)
    }

    private fun onShareNewsClicked(news: News) {
        viewModel.shareNews(news)
    }

    private fun onOpenNewsClicked(news: News) {
        viewModel.openNewsLink(news)
    }
}