package com.williamzabot.queens.domain.model

import java.io.Serializable

data class QueenItem(
    val id: Int,
    val image_url: String,
    val missCongeniality: Boolean,
    val name: String,
    val quote: String,
    val winner: Boolean,
    var favorite : Boolean = false
) : Serializable