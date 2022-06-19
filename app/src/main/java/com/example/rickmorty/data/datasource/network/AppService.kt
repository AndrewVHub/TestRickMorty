package com.example.rickmorty.data.datasource.network

import com.example.rickmorty.data.model.Character
import com.example.rickmorty.data.model.CharactersList
import retrofit2.http.GET

interface AppService {

    companion object{
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }

    @GET("character")
    suspend fun getCharacters(): CharactersList
}