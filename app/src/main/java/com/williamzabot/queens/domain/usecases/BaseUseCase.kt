package com.williamzabot.queens.domain.usecases

import com.williamzabot.queens.domain.utils.Result

abstract class BaseUseCase<T : Any, in Params> {
    abstract suspend fun execute(params: Params?): Result<T>
}