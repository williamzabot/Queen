package com.williamzabot.queens.presenter.features.queens

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.williamzabot.queens.domain.model.QueenItem
import com.williamzabot.queens.domain.repositories.FavoriteRepository
import com.williamzabot.queens.domain.usecases.QueenUseCase
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val queenUseCase: QueenUseCase
) :
    ViewModel() {

    val queens = Pager(
        config = PagingConfig(
            pageSize = 10,
            enablePlaceholders = false
        ),
        pagingSourceFactory = { QueenPagingSource(queenUseCase) }
    ).flow.cachedIn(viewModelScope)
}