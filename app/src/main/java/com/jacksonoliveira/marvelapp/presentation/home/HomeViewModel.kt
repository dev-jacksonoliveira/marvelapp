package com.jacksonoliveira.marvelapp.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jacksonoliveira.marvelapp.data.model.Comic
import com.jacksonoliveira.marvelapp.data.repository.ComicsRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(private val comicsRepository: ComicsRepository) : ViewModel() {
    private val _state: MutableLiveData<PagingData<Comic>> = MutableLiveData()
    val state: LiveData<PagingData<Comic>> = _state

    init {
        loadComicsData()
    }

    fun loadComicsData() {
        viewModelScope.launch {
            comicsRepository.getComicsList()
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _state.value = pagingData
                }
        }
    }
}

