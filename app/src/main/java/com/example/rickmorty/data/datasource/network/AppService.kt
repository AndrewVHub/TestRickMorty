package com.example.rickmorty.data.datasource.network

import com.example.rickmorty.data.model.*
import retrofit2.http.GET

interface AppService {

    companion object{
        const val BASE_URL = "https://rickandmortyapi.com/api/"
    }

    @GET("character")
    suspend fun getCharacters(): ApiResponse<Character>

    @GET("location")
    suspend fun getLocations(): ApiResponse<Location>

    @GET("episode")
    suspend fun getEpisode(): ApiResponse<Episode>
}