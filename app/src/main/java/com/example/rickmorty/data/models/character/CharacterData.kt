package com.example.rickmorty.data.models.character

import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("species") val species: String,
    @SerializedName("gender") val gender: String,
    @SerializedName("location") val location: LocationCharacterResponse,
    @SerializedName("image") val image: String
)

data class CharacterModel(
    val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val location: LocationCharacterResponse,
    val image: String
)


data class LocationCharacterResponse(
    val name: String,
    val url: String
)
