package com.williamzabot.queens.domain.repositories

import com.williamzabot.queens.domain.model.QueenItem

interface FavoriteRepository {

    suspend fun insertFavorite(queen : QueenItem): Long

    suspend fun deleteFavorite(id: Long)

    suspend fun getFavorites(): List<QueenItem>
}