package com.example.rickmorty.domain.interactor

import com.example.rickmorty.data.model.ApiResponse
import com.example.rickmorty.data.model.Episode
import com.example.rickmorty.domain.repository.EpisodeRepository

class GetEpisodeUseCase(
    private val repository: EpisodeRepository
) {
    suspend fun execute(): ApiResponse<Episode> = repository.getEpisode()
}