package com.jacksonoliveira.marvelapp.data.network

import com.jacksonoliveira.marvelapp.data.model.Comic


data class ComicDTO (
    val id: Int,
    val title: String,
    val description: String?,
    val thumbnail: ThumbnailDTO
) {
    fun converterToModel() : Comic = Comic(
        id = id,
        title = title,
        description = description,
        thumbnail = thumbnail.converterToModel()
    )
}