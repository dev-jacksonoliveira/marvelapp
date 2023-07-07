package com.jacksonoliveira.marvelapp.data.network

import com.jacksonoliveira.marvelapp.data.model.ComicsResponse

data class ComicsResponseDTO(
    val data: ComicsDataDTO? = null
) {
    fun converterToModel(): ComicsResponse = ComicsResponse(data = data?.converterToModel())
}