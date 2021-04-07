package com.williamzabot.queens.presenter.features.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.williamzabot.queens.domain.model.QueenItem
import com.williamzabot.queens.domain.repositories.FavoriteRepository
import kotlinx.coroutines.launch

class DetailViewModel(private val favoriteRepository: FavoriteRepository) :
    ViewModel() {

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun checkIsFavorite(queen: QueenItem) {
        viewModelScope.launch {
            if (favoriteRepository.getFavorites().contains(queen)) {
                _isFavorite.postValue(true)
            }
        }
    }

    fun favorite(queen: QueenItem) {
        viewModelScope.launch {
            favoriteRepository.insertFavorite(queen)
        }
    }
}