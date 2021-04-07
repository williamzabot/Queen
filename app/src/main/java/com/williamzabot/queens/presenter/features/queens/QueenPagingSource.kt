package com.williamzabot.queens.presenter.features.queens

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.williamzabot.queens.domain.model.QueenItem
import com.williamzabot.queens.domain.usecases.QueenUseCase
import com.williamzabot.queens.domain.utils.LIMIT_PAGE
import com.williamzabot.queens.domain.utils.Result
import retrofit2.HttpException
import java.io.IOException

class QueenPagingSource(private val queenUseCase: QueenUseCase) : PagingSource<Int, QueenItem>() {

    override fun getRefreshKey(state: PagingState<Int, QueenItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QueenItem> {
        val offset = params.key ?: 1
        return try {
            when (val result = queenUseCase.execute(QueenUseCase.Params(offset))) {
                is Result.Success -> LoadResult.Page(
                    data = result.data,
                    prevKey = null,
                    nextKey = offset + LIMIT_PAGE
                )
                is Result.Failure -> LoadResult.Error(result.exception)
            }
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }
}