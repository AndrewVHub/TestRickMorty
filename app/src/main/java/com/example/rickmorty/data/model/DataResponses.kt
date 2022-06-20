package com.example.rickmorty.data.model

import com.google.gson.annotations.SerializedName


data class ApiResponse<T>(
    @SerializedName("results") val results: List<T>
)

data class Character(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("species") val species: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("location") val location: LocationResponse,
    @SerializedName("image") val image: String
)
data class LocationResponse(
    val name: String,
    val url: String
)

data class Location(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("dimension") val dimension: String
)

data class Episode(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("air_date") val airDate: String,
    @SerializedName("episode") val episode: String
)