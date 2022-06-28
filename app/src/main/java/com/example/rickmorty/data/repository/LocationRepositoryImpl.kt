package com.example.rickmorty.data.repository

import com.example.rickmorty.data.datasource.RemoteDataSource
import com.example.rickmorty.data.models.location.Location
import com.example.rickmorty.domain.repository.LocationRepository

class LocationRepositoryImpl(
    private val dataSource: RemoteDataSource
): LocationRepository {
    override suspend fun getLocation(): List<Location> = dataSource.getLocations()
}