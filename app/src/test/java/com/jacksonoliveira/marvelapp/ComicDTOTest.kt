package com.jacksonoliveira.marvelapp

import com.jacksonoliveira.marvelapp.data.model.Comic
import com.jacksonoliveira.marvelapp.data.model.ComicsResponse
import com.jacksonoliveira.marvelapp.data.network.ComicDTO
import com.jacksonoliveira.marvelapp.data.network.ComicsDataDTO
import com.jacksonoliveira.marvelapp.data.network.ComicsResponseDTO
import com.jacksonoliveira.marvelapp.data.network.ThumbnailDTO
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ComicDTOTest {

    private val thumbnailDto = ThumbnailDTO(
        path = "http://i.annihil.us/u/prod/marvel/i/mg/2/f0/4bc6670c80007",
        extension = "jpg"
    )

    private val comicDto = ComicDTO(
        id = 1158,
        title = "ULTIMATE X-MEN VOL. 5: ULTIMATE WAR TPB (Trade Paperback)",
        description = null,
        thumbnail = thumbnailDto
    )

    private val comicsDataDto = ComicsDataDTO(
        results = listOf(comicDto)
    )

    private val comicsResponseDto = ComicsResponseDTO(
        data = comicsDataDto
    )

    @Test
    fun shouldConverter_entityModel() {
        val comic: Comic = comicDto.converterToModel()

        assertTrue(true)

        assertTrue(comic.title == comicDto.title)
    }

    @Test
    fun shouldConverter_entityModelComicsResponse() {
        val comicsResponse: ComicsResponse = comicsResponseDto.converterToModel()

        assertTrue(true)

        assertTrue(comicsResponse.data == comicsResponseDto.data?.converterToModel())
    }
}