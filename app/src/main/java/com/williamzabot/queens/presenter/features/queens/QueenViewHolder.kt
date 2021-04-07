package com.williamzabot.queens.presenter.features.queens

import android.annotation.SuppressLint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.williamzabot.queens.databinding.ItemQueenBinding
import com.williamzabot.queens.domain.model.QueenItem
import com.williamzabot.queens.presenter.extensions.urlImage
import com.williamzabot.queens.presenter.extensions.visible

class QueenViewHolder(private val binding: ItemQueenBinding) :
    RecyclerView.ViewHolder(binding.root) {

    @SuppressLint("SetTextI18n")
    fun bind(queen: QueenItem) {
        binding.apply {
            imageItemQueen.urlImage(queen.image_url)
            nameItemQueen.text = queen.name
            quoteItemQueen.text = if (queen.quote.isNotEmpty() && queen.quote != "\"\"") {
                "\"${queen.quote}\""
            } else {
                "not a message"
            }
            if (queen.winner) {
                winnerAnimation.visible(true)
                textWinner.visible(true)
            }
        }

    }
}