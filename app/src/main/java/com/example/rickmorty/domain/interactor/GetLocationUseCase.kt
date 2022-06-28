package com.example.rickmorty.domain.interactor

import com.example.rickmorty.data.models.location.Location
import com.example.rickmorty.domain.repository.LocationRepository

class GetLocationUseCase(
    private val repository: LocationRepository
) {
    suspend fun execute(): List<Location> = repository.getLocation()
}