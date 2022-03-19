package com.ingrid.newssoccer.ui.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ingrid.newssoccer.databinding.FragmentFavoritesBinding
import com.ingrid.newssoccer.model.News
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoritesFragment : Fragment() {

    private val favoriteAdapter =
        FavoriteAdapter(::onFavoriteClicked, ::onOpenNewsClicked, ::onShareNewsClicked)
    private lateinit var binding: FragmentFavoritesBinding
    private val viewModel by viewModel<FavoritesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        binding.rvFavorite.adapter = favoriteAdapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
    }

    private fun initViewModel() {
        viewModel.getFavoriteList().observe(viewLifecycleOwner, ::updateFavorites)
    }

    private fun updateFavorites(favorites: List<News>) {
        favoriteAdapter.updateFavorites(favorites)
    }

    private fun onFavoriteClicked(news: News) {
        viewModel.removeFavoriteNews(news)
    }

    private fun onShareNewsClicked(news: News) {
        viewModel.shareNews(news)
    }

    private fun onOpenNewsClicked(news: News) {
        viewModel.openNewsLink(news)
    }
}