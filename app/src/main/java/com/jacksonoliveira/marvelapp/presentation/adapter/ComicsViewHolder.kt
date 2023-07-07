package com.jacksonoliveira.marvelapp.presentation.adapter

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.BitmapTransitionOptions.withCrossFade
import com.jacksonoliveira.marvelapp.data.model.Comic
import com.jacksonoliveira.marvelapp.databinding.ComicsItemViewBinding

class ComicsViewHolder(
    private val binding: ComicsItemViewBinding,
    private val onItemClick: ((Comic) -> Unit)?
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(itemComics: Comic) = with(binding) {
        Glide.with(root)
            .asBitmap()
            .load(itemComics.thumbnail?.imagePath)
            .transition(withCrossFade())
            .into(binding.imageViewPath)
        imageViewPath.setOnClickListener { onItemClick?.let { item -> item(itemComics) } }
    }
}