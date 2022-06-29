package com.example.rickmorty.presentation.mapper

import com.example.rickmorty.data.models.episode.EpisodeModel
import com.example.rickmorty.data.models.episode.EpisodeResponse
import com.example.rickmorty.presentation.mapper.base.Mapper

object EpisodeMapper: Mapper<EpisodeResponse, EpisodeModel>() {
    override fun map(from: EpisodeResponse): EpisodeModel = from.run {
        EpisodeModel(
            id, name, airDate, episode
        )
    }
}