package com.williamzabot.queens.presenter.features.queens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.williamzabot.queens.domain.usecases.QueenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
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