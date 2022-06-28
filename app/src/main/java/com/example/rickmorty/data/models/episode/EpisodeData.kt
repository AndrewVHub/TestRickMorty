package com.example.rickmorty.data.models.episode

import com.google.gson.annotations.SerializedName

data class EpisodeResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("air_date") val airDate: String,
    @SerializedName("episode") val episode: String
)

data class Episode(
    val id: Int,
    val name: String,
    val airDate: String,
    val episode: String
)
