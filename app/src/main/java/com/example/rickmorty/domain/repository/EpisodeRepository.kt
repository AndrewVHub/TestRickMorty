package com.example.rickmorty.domain.repository

import com.example.rickmorty.data.model.ApiResponse
import com.example.rickmorty.data.model.Episode

interface EpisodeRepository {
    suspend fun getEpisode(): ApiResponse<Episode>
}