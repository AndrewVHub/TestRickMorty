package com.example.rickmorty.domain.repository

import com.example.rickmorty.data.model.ApiResponse
import com.example.rickmorty.data.model.Location

interface LocationRepository {
    suspend fun getLocation(): ApiResponse<Location>
}