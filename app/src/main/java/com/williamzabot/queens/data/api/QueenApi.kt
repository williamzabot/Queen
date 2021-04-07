package com.williamzabot.queens.data.api

import com.williamzabot.queens.domain.model.Queen
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QueenApi {

    @GET("queens")
    suspend fun getQueens(
        @Query("limit") limit: Int,
        @Query("offset") offset : Int
    ): Response<Queen>

}