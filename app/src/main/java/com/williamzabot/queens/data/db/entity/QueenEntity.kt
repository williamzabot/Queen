package com.williamzabot.queens.data.db.entity


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.williamzabot.queens.domain.model.QueenItem
import java.io.Serializable

@Entity(tableName = "favorites")
data class QueenEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val idOriginal: Int,
    val image_url: String,
    val missCongeniality: Boolean,
    val name: String,
    val quote: String,
    val winner: Boolean
) : Serializable

fun QueenEntity.toQueenItem(): QueenItem {
    return QueenItem(
        idOriginal, image_url, missCongeniality, name, quote, winner
    )
}