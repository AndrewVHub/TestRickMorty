package com.example.rickmorty.domain.interactor

import com.example.rickmorty.data.models.episode.Episode
import com.example.rickmorty.domain.repository.EpisodeRepository

class GetEpisodeUseCase(
    private val repository: EpisodeRepository
) {
    suspend fun execute(): List<Episode> = repository.getEpisode()
}