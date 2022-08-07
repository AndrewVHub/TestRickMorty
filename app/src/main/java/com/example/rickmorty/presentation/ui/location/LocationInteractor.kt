package com.example.rickmorty.presentation.ui.location

import com.example.rickmorty.data.models.location.LocationModel
import com.example.rickmorty.data.network.ApiWorker
import com.example.rickmorty.presentation.mapper.LocationMapper
import javax.inject.Inject

class LocationInteractor @Inject constructor(
    private val apiWorker: ApiWorker
) {
    suspend fun getLocationList(): List<LocationModel> = apiWorker.getLocations().map {
        LocationMapper.map(it)
    }
}