package com.williamzabot.queens.presenter.features.queens

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.williamzabot.queens.R
import com.williamzabot.queens.databinding.LoadStateItemBinding

class NewsLoadStateAdapter : LoadStateAdapter<NewsLoadStateAdapter.LoadStateViewHolder>() {

    override fun onBindViewHolder(
        holder: LoadStateViewHolder,
        loadState: LoadState
    ) = holder.bind(loadState)

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.load_state_item, parent, false)
        val binding = LoadStateItemBinding.bind(view)
        return LoadStateViewHolder(binding)
    }

    class LoadStateViewHolder(binding: LoadStateItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val progressBar = binding.progressbarItem

        fun bind(loadState: LoadState) {
            progressBar.isVisible = loadState is LoadState.Loading
        }
    }
}


