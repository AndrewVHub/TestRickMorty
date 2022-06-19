package com.example.rickmorty.data.model

import com.google.gson.annotations.SerializedName


data class CharactersList(
    @SerializedName("results") val results: List<Character>
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
