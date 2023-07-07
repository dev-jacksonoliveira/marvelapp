package com.jacksonoliveira.marvelapp.application

import android.app.Application
import com.jacksonoliveira.marvelapp.data.di.networkModule
import com.jacksonoliveira.marvelapp.data.di.repositoryModule
import com.jacksonoliveira.marvelapp.data.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarvelApp : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MarvelApp)
            modules(
                listOf(
                    networkModule,
                    repositoryModule,
                    viewModelModule
                )
            )
        }
    }
}