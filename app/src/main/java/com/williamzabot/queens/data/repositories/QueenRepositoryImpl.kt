package com.williamzabot.queens.data.repositories

import com.williamzabot.queens.data.di.RetrofitModule.provideQueenApi
import com.williamzabot.queens.domain.model.Queen
import com.williamzabot.queens.domain.repositories.QueenRepository
import com.williamzabot.queens.domain.utils.LIMIT_PAGE
import com.williamzabot.queens.domain.utils.Result
import javax.inject.Inject

class QueenRepositoryImpl @Inject constructor() : QueenRepository {

    private val queenApi = provideQueenApi()

    override suspend fun getQueens(offset: Int): Result<Queen> {
        val response = queenApi.getQueens(LIMIT_PAGE, offset)
        return when (response.code()) {
            200 -> Result.Success(response.body()!!)
            else -> Result.Failure(Exception())
        }
    }


}