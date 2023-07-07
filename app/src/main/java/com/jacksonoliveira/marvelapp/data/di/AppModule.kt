package com.jacksonoliveira.marvelapp.data.di

import com.jacksonoliveira.marvelapp.data.api.MarvelApiClient
import com.jacksonoliveira.marvelapp.data.repository.ComicsRepository
import com.jacksonoliveira.marvelapp.data.repository.ComicsRepositoryImpl
import com.jacksonoliveira.marvelapp.presentation.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val networkModule = module {
    factory { MarvelApiClient.create() }
}

val repositoryModule = module {
    single<ComicsRepository> { ComicsRepositoryImpl(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
}
