package com.williamzabot.queens.presenter.features.queens

import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.williamzabot.queens.databinding.ItemQueenBinding.inflate
import com.williamzabot.queens.domain.model.QueenItem

class QueenAdapter(private val clickQueen: (queen: QueenItem) -> Unit) :
    PagingDataAdapter<QueenItem, QueenViewHolder>(QUEEN_COMPARATOR) {

    companion object {
        private val QUEEN_COMPARATOR = object : DiffUtil.ItemCallback<QueenItem>() {
            override fun areItemsTheSame(oldItem: QueenItem, newItem: QueenItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: QueenItem, newItem: QueenItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: QueenViewHolder, position: Int) {
        getItem(position)?.let { queen ->
            holder.bind(queen)
            holder.itemView.setOnClickListener {
                clickQueen(queen)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QueenViewHolder {
        return QueenViewHolder(inflate(from(parent.context), parent, false))
    }
}