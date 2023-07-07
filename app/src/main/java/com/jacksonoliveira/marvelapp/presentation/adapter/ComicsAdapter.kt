package com.jacksonoliveira.marvelapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.jacksonoliveira.marvelapp.data.model.Comic
import com.jacksonoliveira.marvelapp.databinding.ComicsItemViewBinding
import com.jacksonoliveira.marvelapp.presentation.diffutil.ComicsDiffCallback

class ComicsAdapter(private val onItemClick: (Comic) -> Unit) :
    PagingDataAdapter<Comic, ComicsViewHolder>(ComicsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComicsViewHolder {
        val binding = ComicsItemViewBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ComicsViewHolder(binding, onItemClick)
    }

    override fun onBindViewHolder(holder: ComicsViewHolder, position: Int) {
        getItem(position)?.let { items -> holder.bind(items) }
    }
}