package com.example.rickmorty.presentation.ui.episode

import com.example.rickmorty.data.network.ApiWorker
import com.example.rickmorty.data.models.episode.EpisodeModel
import com.example.rickmorty.presentation.mapper.EpisodeMapper
import javax.inject.Inject

class EpisodeInteractor @Inject constructor(
    private val apiWorker: ApiWorker
) {
    suspend fun getEpisodeList(): List<EpisodeModel> = apiWorker.getEpisode().map {
        EpisodeMapper.map(it)
    }
}