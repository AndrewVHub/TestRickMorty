package com.example.rickmorty.domain.repository

import com.example.rickmorty.data.models.episode.Episode

interface EpisodeRepository {
    suspend fun getEpisode(): List<Episode>
}