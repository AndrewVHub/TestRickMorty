package com.example.rickmorty.di

import com.example.rickmorty.data.datasource.RemoteDataSource
import com.example.rickmorty.data.datasource.network.AppService
import com.example.rickmorty.data.repository.CharacterRepositoryImpl
import com.example.rickmorty.domain.interactor.GetCharactersUseCase
import com.example.rickmorty.domain.repository.CharacterRepository
import com.example.rickmorty.presentation.ui.characters.CharacterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val serviceModule = module {
    single<AppService> {
        Retrofit.Builder()
            .baseUrl(AppService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build().create(AppService::class.java)
    }
}

val characterModule = module {
    single<CharacterRepository> { CharacterRepositoryImpl(get())}
    single { RemoteDataSource(get()) }
    single { GetCharactersUseCase(get()) }
}

val viewmodelModule = module {
    viewModel { CharacterViewModel(get()) }
}