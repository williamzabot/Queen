package com.williamzabot.queens.domain.repositories

import com.williamzabot.queens.domain.model.Queen
import com.williamzabot.queens.domain.utils.Result

interface QueenRepository {
    suspend fun getQueens(offset : Int) : Result<Queen>
}