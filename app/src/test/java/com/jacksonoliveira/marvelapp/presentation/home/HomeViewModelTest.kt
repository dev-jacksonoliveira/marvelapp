package com.jacksonoliveira.marvelapp.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.jacksonoliveira.marvelapp.data.model.Comic
import com.jacksonoliveira.marvelapp.data.repository.ComicsRepository
import com.jacksonoliveira.marvelapp.util.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: HomeViewModel
    private lateinit var comicsRepository: ComicsRepository

    private val stateObserver = mockk<Observer<PagingData<Comic>>>(relaxed = true)

    @Before
    fun setup() {
        comicsRepository = mockk()
        viewModel = HomeViewModel(comicsRepository)
        viewModel.state.observeForever(stateObserver)
        mainDispatcherRule
    }

    @Test
    fun loadComicsData_should_emitPagingData() = runTest {
        val pagingData = PagingData.from(
            listOf(
                Comic(1, "X-men"),
                Comic(2, "Spider Man"),
                Comic(3, "Black Panther")
            )
        )
        coEvery { comicsRepository.getComicsList() } returns flowOf(pagingData)

        val observer = mockk<Observer<PagingData<Comic>>>(relaxed = true)
        viewModel.state.observeForever(observer)

        viewModel.loadComicsData()

        coVerify { observer.onChanged(pagingData) }
    }
}
