package com.example.rickmorty.data.repository

import com.example.rickmorty.data.datasource.RemoteDataSource
import com.example.rickmorty.data.models.episode.Episode
import com.example.rickmorty.domain.repository.EpisodeRepository

class EpisodeRepositoryImpl(
    private val dataSource: RemoteDataSource
): EpisodeRepository {
    override suspend fun getEpisode(): List<Episode> = dataSource.getEpisode()
}