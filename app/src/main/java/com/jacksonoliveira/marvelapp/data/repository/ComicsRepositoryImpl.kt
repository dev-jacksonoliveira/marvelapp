package com.jacksonoliveira.marvelapp.data.repository


import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.jacksonoliveira.marvelapp.data.model.Comic
import com.jacksonoliveira.marvelapp.data.services.ComicsPagingSource
import com.jacksonoliveira.marvelapp.data.services.MarvelApiService
import com.jacksonoliveira.marvelapp.data.services.MarvelApiService.Companion.LIMIT_ITEMS
import kotlinx.coroutines.flow.Flow

class ComicsRepositoryImpl(private val apiService: MarvelApiService) : ComicsRepository {
    override suspend fun getComicsList(): Flow<PagingData<Comic>> {
        return Pager(
            config = PagingConfig(
                pageSize = LIMIT_ITEMS,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ComicsPagingSource(apiService) }
        ).flow
    }
}
