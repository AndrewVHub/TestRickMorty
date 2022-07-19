package com.example.rickmorty

import android.app.Application
import com.example.rickmorty.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class RickMortyApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@RickMortyApplication)
            modules(listOf(serviceModule, viewmodelModule, interactorsModule, networkModule, downloadModule))
        }
    }
}