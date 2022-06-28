package com.example.rickmorty.domain.repository

import com.example.rickmorty.data.models.location.Location

interface LocationRepository {
    suspend fun getLocation(): List<Location>
}