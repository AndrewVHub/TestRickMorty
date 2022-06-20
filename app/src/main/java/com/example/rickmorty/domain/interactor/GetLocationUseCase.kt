package com.example.rickmorty.domain.interactor

import com.example.rickmorty.data.model.ApiResponse
import com.example.rickmorty.data.model.Location
import com.example.rickmorty.domain.repository.LocationRepository

class GetLocationUseCase(
    private val repository: LocationRepository
) {
    suspend fun execute(): ApiResponse<Location> = repository.getLocation()
}