package com.williamzabot.queens.domain.usecases

import com.williamzabot.queens.domain.model.QueenItem
import com.williamzabot.queens.domain.repositories.QueenRepository
import com.williamzabot.queens.domain.utils.Result
import java.lang.IllegalArgumentException
import javax.inject.Inject

class QueenUseCase @Inject constructor(private val queenRepository: QueenRepository) :
    BaseUseCase<List<QueenItem>, QueenUseCase.Params>() {

    data class Params(val offset: Int)

    override suspend fun execute(params: Params?): Result<List<QueenItem>> {
        if (params == null) throw IllegalArgumentException()
        return when (val result = queenRepository.getQueens(params.offset)) {
            is Result.Success -> Result.Success(result.data)
            is Result.Failure -> Result.Failure(result.exception)
        }
    }
}