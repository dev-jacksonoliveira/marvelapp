package com.jacksonoliveira.marvelapp.data.network

import com.jacksonoliveira.marvelapp.data.model.Thumbnail

data class ThumbnailDTO (
    val path: String? = null,
    val extension: String? = null
) {
    fun converterToModel() : Thumbnail = Thumbnail(path = path, extension = extension)
}
