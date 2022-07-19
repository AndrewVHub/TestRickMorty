package com.example.rickmorty.data.network

import com.example.rickmorty.data.models.character.CharacterResponse
import com.example.rickmorty.data.models.episode.EpisodeResponse
import com.example.rickmorty.data.models.location.LocationResponse

interface ApiWorker {
    suspend fun getCharacters(): List<CharacterResponse>

    suspend fun getLocations(): List<LocationResponse>

    suspend fun getEpisode(): List<EpisodeResponse>
}