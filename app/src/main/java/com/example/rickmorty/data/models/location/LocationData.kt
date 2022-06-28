package com.example.rickmorty.data.models.location

import com.google.gson.annotations.SerializedName

data class LocationResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("type") val type: String,
    @SerializedName("dimension") val dimension: String
)

data class Location(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String
)
