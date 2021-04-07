package com.williamzabot.queens.presenter.features.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.williamzabot.queens.domain.model.QueenItem
import com.williamzabot.queens.domain.repositories.FavoriteRepository
import kotlinx.coroutines.launch

class FavoritesViewModel(private val favoriteRepository: FavoriteRepository) :
    ViewModel() {

    private val _favorites = MutableLiveData<List<QueenItem>>()
    val favorites: LiveData<List<QueenItem>> = _favorites


    fun getFavorites() {
        viewModelScope.launch {
            _favorites.postValue(favoriteRepository.getFavorites())
        }
    }

}