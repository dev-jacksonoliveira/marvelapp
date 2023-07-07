package com.jacksonoliveira.marvelapp.data.network

import com.jacksonoliveira.marvelapp.data.model.ComicsData

data class ComicsDataDTO(
    val results: List<ComicDTO>? = null
) {
    fun converterToModel(): ComicsData =
        ComicsData(results = results?.map { it.converterToModel() })
}
