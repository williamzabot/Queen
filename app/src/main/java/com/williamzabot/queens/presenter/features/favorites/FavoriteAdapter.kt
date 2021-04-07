package com.williamzabot.queens.presenter.features.favorites

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.williamzabot.queens.databinding.ItemQueenBinding
import com.williamzabot.queens.domain.model.QueenItem
import com.williamzabot.queens.presenter.features.queens.QueenViewHolder

class FavoriteAdapter : RecyclerView.Adapter<QueenViewHolder>() {

    var favorites = listOf<QueenItem>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueenViewHolder {
        return QueenViewHolder(
            ItemQueenBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: QueenViewHolder, position: Int) {
        holder.bind(favorites[position])
    }

    override fun getItemCount() = favorites.size


}