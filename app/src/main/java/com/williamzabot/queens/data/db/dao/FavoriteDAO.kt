package com.williamzabot.queens.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.williamzabot.queens.data.db.entity.QueenEntity

@Dao
interface FavoriteDAO {

    @Insert
    suspend fun insert(queenEntity: QueenEntity): Long

    @Query("DELETE FROM favorites WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("SELECT * FROM favorites")
    suspend fun getFavorites(): List<QueenEntity>

}