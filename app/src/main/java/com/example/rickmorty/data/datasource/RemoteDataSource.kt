package com.example.rickmorty.data.datasource

import com.example.rickmorty.data.datasource.network.AppService
import com.example.rickmorty.data.models.character.Character
import com.example.rickmorty.data.models.episode.Episode
import com.example.rickmorty.data.models.location.Location
import com.example.rickmorty.presentation.utils.toModel

class RemoteDataSource(
    private val service: AppService
) {
    suspend fun getCharacters(): List<Character> = service.getCharacters().results.map {
        it.toModel()
    }
    suspend fun getLocations(): List<Location> = service.getLocations().results.map {
        it.toModel()
    }
    suspend fun getEpisode(): List<Episode> = service.getEpisode().results.map {
        it.toModel()
    }
}