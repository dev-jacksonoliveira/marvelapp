package com.jacksonoliveira.marvelapp.presentation.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.jacksonoliveira.marvelapp.data.model.Comic

class ComicsDiffCallback : DiffUtil.ItemCallback<Comic>() {
    override fun areItemsTheSame(oldItem: Comic, newItem: Comic): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Comic, newItem: Comic): Boolean {
        return oldItem == newItem
    }
}