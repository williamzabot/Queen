package com.williamzabot.queens.data.repositories

import com.williamzabot.queens.data.api.QueenApi
import com.williamzabot.queens.data.di.RetrofitModule
import com.williamzabot.queens.domain.model.Queen
import com.williamzabot.queens.domain.repositories.QueenRepository
import com.williamzabot.queens.domain.utils.LIMIT_PAGE
import com.williamzabot.queens.domain.utils.Result
import javax.inject.Inject

class QueenRepositoryImpl @Inject constructor() : QueenRepository {

    private val queenApi = RetrofitModule.retrofitInstance().create(QueenApi::class.java)

    override suspend fun getQueens(offset: Int): Result<Queen> {
        val response = queenApi.getQueens(LIMIT_PAGE, offset)
        return when (response.code()) {
            200 -> Result.Success(response.body()!!)
            else -> Result.Failure(Exception())
        }
    }


}