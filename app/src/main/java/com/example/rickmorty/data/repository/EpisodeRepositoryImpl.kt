package com.example.rickmorty.data.repository

import com.example.rickmorty.data.datasource.RemoteDataSource
import com.example.rickmorty.data.model.ApiResponse
import com.example.rickmorty.data.model.Episode
import com.example.rickmorty.domain.repository.EpisodeRepository

class EpisodeRepositoryImpl(
    private val dataSource: RemoteDataSource
): EpisodeRepository {
    override suspend fun getEpisode(): ApiResponse<Episode> = dataSource.getEpisode()
}