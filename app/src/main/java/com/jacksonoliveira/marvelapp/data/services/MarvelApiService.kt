package com.jacksonoliveira.marvelapp.data.services

import com.jacksonoliveira.marvelapp.BuildConfig.MARVEL_PUBLIC_API_KEY
import com.jacksonoliveira.marvelapp.data.network.ComicsResponseDTO
import com.jacksonoliveira.marvelapp.presentation.util.generatorHashMd5
import retrofit2.http.GET
import retrofit2.http.Query

interface MarvelApiService {
    @GET("comics")
    suspend fun getComics(
        @Query("ts") tsValue: Long = TS_DEFAULT,
        @Query("apikey") apiKey: String = MARVEL_PUBLIC_API_KEY,
        @Query("hash") hash: String = HASH,
        @Query("limit") limit: Int = LIMIT_ITEMS,
        @Query("offset") offset: Int = OFFSET,
    ): ComicsResponseDTO

    companion object {
        const val TS_DEFAULT = 1L
        val HASH = generatorHashMd5()
        const val LIMIT_ITEMS = 20
        const val OFFSET = 0
    }
}

