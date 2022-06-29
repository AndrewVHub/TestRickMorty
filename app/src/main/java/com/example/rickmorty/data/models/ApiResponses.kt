package com.example.rickmorty.data.models

import com.google.gson.annotations.SerializedName


data class ApiResponse<T>(
    @SerializedName("results") val results: List<T>
)


