package com.williamzabot.queens.presenter.features.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.williamzabot.queens.R
import com.williamzabot.queens.data.db.AppDatabase
import com.williamzabot.queens.data.repositories.FavoriteRepositoryImpl
import com.williamzabot.queens.databinding.FragmentFavoritesBinding
import com.williamzabot.queens.domain.repositories.FavoriteRepository
import com.williamzabot.queens.presenter.extensions.visible

class FavoritesFragment : Fragment() {

    private var _binding: FragmentFavoritesBinding? = null
    private val binding get() = _binding!!
    private val favoriteAdapter by lazy { FavoriteAdapter() }
    private val viewModel by viewModels<FavoritesViewModel> {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return FavoritesViewModel(
                    FavoriteRepositoryImpl(
                        AppDatabase.getInstance(requireContext()).favoriteDAO
                    ) as FavoriteRepository
                ) as T
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visible(true)
        binding.recyclerviewFavorites.adapter = favoriteAdapter
        observeEvents()
        viewModel.getFavorites()
    }

    private fun observeEvents() {
        viewModel.favorites.observe(viewLifecycleOwner) { queens ->
            favoriteAdapter.favorites = queens
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}