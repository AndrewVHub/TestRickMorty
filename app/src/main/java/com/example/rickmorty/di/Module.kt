package com.example.rickmorty.di

import com.example.rickmorty.BuildConfig
import com.example.rickmorty.data.datasource.RemoteDataSource
import com.example.rickmorty.data.datasource.network.AppService
import com.example.rickmorty.data.repository.CharacterRepositoryImpl
import com.example.rickmorty.data.repository.EpisodeRepositoryImpl
import com.example.rickmorty.data.repository.LocationRepositoryImpl
import com.example.rickmorty.domain.interactor.GetCharactersUseCase
import com.example.rickmorty.domain.interactor.GetEpisodeUseCase
import com.example.rickmorty.domain.interactor.GetLocationUseCase
import com.example.rickmorty.domain.repository.CharacterRepository
import com.example.rickmorty.domain.repository.EpisodeRepository
import com.example.rickmorty.domain.repository.LocationRepository
import com.example.rickmorty.presentation.ui.characters.CharacterViewModel
import com.example.rickmorty.presentation.ui.episode.EpisodeViewModel
import com.example.rickmorty.presentation.ui.location.LocationViewModel
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
    single { RemoteDataSource(get()) }
}

val characterModule = module {
    single<CharacterRepository> { CharacterRepositoryImpl(get())}
    single { GetCharactersUseCase(get()) }
}

val locationModule = module {
    single<LocationRepository> { LocationRepositoryImpl(get())}
    single { GetLocationUseCase(get()) }
}

val episodeModule = module {
    single<EpisodeRepository> { EpisodeRepositoryImpl(get()) }
    single { GetEpisodeUseCase(get()) }
}

val viewmodelModule = module {
    viewModel { CharacterViewModel(get()) }
    viewModel { LocationViewModel(get()) }
    viewModel { EpisodeViewModel(get()) }
}