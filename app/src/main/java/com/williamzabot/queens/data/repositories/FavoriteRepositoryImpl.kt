package com.williamzabot.queens.data.repositories

import com.williamzabot.queens.data.db.dao.FavoriteDAO
import com.williamzabot.queens.data.db.entity.QueenEntity
import com.williamzabot.queens.data.db.entity.toQueenItem
import com.williamzabot.queens.domain.model.QueenItem
import com.williamzabot.queens.domain.repositories.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(private val favoriteDAO: FavoriteDAO) :
    FavoriteRepository {

    override suspend fun insertFavorite(queen: QueenItem): Long {
        return favoriteDAO.insert(
            QueenEntity(
                idOriginal = queen.id,
                image_url = queen.image_url,
                missCongeniality = queen.missCongeniality,
                name = queen.name,
                quote = queen.quote,
                winner = queen.winner
            )
        )
    }

    override suspend fun deleteFavorite(id: Long) {
        favoriteDAO.delete(id)
    }

    override suspend fun getFavorites(): List<QueenItem> {
        return favoriteDAO.getFavorites().map {
            it.toQueenItem()
        }
    }
}