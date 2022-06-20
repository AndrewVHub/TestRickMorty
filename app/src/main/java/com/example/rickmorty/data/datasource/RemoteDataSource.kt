package com.example.rickmorty.data.datasource

import com.example.rickmorty.data.datasource.network.AppService
import com.example.rickmorty.data.model.Character
import com.example.rickmorty.data.model.ApiResponse
import com.example.rickmorty.data.model.Episode
import com.example.rickmorty.data.model.Location

class RemoteDataSource(
    private val service: AppService
) {
    suspend fun getCharacters(): ApiResponse<Character> = service.getCharacters()
    suspend fun getLocations(): ApiResponse<Location> = service.getLocations()
    suspend fun getEpisode(): ApiResponse<Episode> = service.getEpisode()
}