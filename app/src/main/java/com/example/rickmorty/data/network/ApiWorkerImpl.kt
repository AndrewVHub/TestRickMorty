package com.example.rickmorty.data.network

import com.example.rickmorty.data.models.character.CharacterResponse
import com.example.rickmorty.data.models.episode.EpisodeResponse
import com.example.rickmorty.data.models.location.LocationResponse

class ApiWorkerImpl(
    private val service: AppService
): ApiWorker {
    override suspend fun getCharacters(): List<CharacterResponse> = service.getCharacters().results

    override suspend fun getLocations(): List<LocationResponse> = service.getLocations().results

    override suspend fun getEpisode(): List<EpisodeResponse> = service.getEpisode().results
}