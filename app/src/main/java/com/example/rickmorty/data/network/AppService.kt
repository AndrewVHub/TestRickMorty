package com.example.rickmorty.data.network

import com.example.rickmorty.data.models.ApiResponse
import com.example.rickmorty.data.models.character.CharacterResponse
import com.example.rickmorty.data.models.episode.EpisodeResponse
import com.example.rickmorty.data.models.location.LocationResponse
import retrofit2.http.GET

interface AppService {

    @GET("character")
    suspend fun getCharacters(): ApiResponse<CharacterResponse>

    @GET("location")
    suspend fun getLocations(): ApiResponse<LocationResponse>

    @GET("episode")
    suspend fun getEpisode(): ApiResponse<EpisodeResponse>
}