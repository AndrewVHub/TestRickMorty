package com.example.rickmorty.data.repository

import com.example.rickmorty.data.datasource.RemoteDataSource
import com.example.rickmorty.data.model.ApiResponse
import com.example.rickmorty.data.model.Location
import com.example.rickmorty.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val dataSource: RemoteDataSource
): LocationRepository {
    override suspend fun getLocation(): ApiResponse<Location> = dataSource.getLocations()
}