package com.jacksonoliveira.marvelapp.data.services

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jacksonoliveira.marvelapp.data.model.Comic
import com.jacksonoliveira.marvelapp.data.services.MarvelApiService.Companion.LIMIT_ITEMS
import com.jacksonoliveira.marvelapp.data.services.MarvelApiService.Companion.OFFSET
import retrofit2.HttpException
import java.io.IOException

class ComicsPagingSource(private val apiService: MarvelApiService) : PagingSource<Int, Comic>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Comic> {
        return try {
            val nextPage = params.key ?: (OFFSET / LIMIT_ITEMS + 1)
            val comicsList = apiService.getComics(offset = nextPage * LIMIT_ITEMS)
            val comics = comicsList.converterToModel().data?.results ?: emptyList()

            LoadResult.Page(
                data = comics,
                prevKey = null,
                nextKey = nextPage + 1
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Comic>): Int? {
        TODO()
    }
}
