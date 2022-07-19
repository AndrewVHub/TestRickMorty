package com.example.rickmorty.di

import com.example.rickmorty.BuildConfig

import com.example.rickmorty.data.network.ApiWorker
import com.example.rickmorty.data.network.ApiWorkerImpl
import com.example.rickmorty.data.network.AppService
import com.example.rickmorty.presentation.ui.base.DownloadImage
import com.example.rickmorty.presentation.ui.characters.CharacterInteractor
import com.example.rickmorty.presentation.ui.characters.CharacterViewModel
import com.example.rickmorty.presentation.ui.episode.EpisodeInteractor
import com.example.rickmorty.presentation.ui.episode.EpisodeViewModel
import com.example.rickmorty.presentation.ui.location.LocationInteractor
import com.example.rickmorty.presentation.ui.location.LocationViewModel
import com.example.rickmorty.presentation.ui.multiple.MultipleViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val serviceModule = module {
    single<AppService> {
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(AppService::class.java)
    }
}

val networkModule = module {
    single<ApiWorker> { ApiWorkerImpl(get()) }
}

val interactorsModule = module {
    single{ CharacterInteractor(get())}
    single{ LocationInteractor(get())}
    single{ EpisodeInteractor(get())}
}

val downloadModule = module {
    single { DownloadImage(androidContext()) }
}

val viewmodelModule = module {
    viewModel { CharacterViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { EpisodeViewModel(get()) }
    viewModel { MultipleViewModel(get()) }
}