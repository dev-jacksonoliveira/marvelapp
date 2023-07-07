package com.jacksonoliveira.marvelapp.data.repository

import androidx.paging.PagingData
import com.jacksonoliveira.marvelapp.data.model.Comic
import kotlinx.coroutines.flow.Flow

interface ComicsRepository {
    suspend fun getComicsList() : Flow<PagingData<Comic>>
}