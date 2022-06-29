package com.example.rickmorty.presentation.mapper

import com.example.rickmorty.data.models.location.LocationModel
import com.example.rickmorty.data.models.location.LocationResponse
import com.example.rickmorty.presentation.mapper.base.Mapper

object LocationMapper: Mapper<LocationResponse, LocationModel>() {
    override fun map(from: LocationResponse): LocationModel = from.run {
        LocationModel(
            id, name, type, dimension
        )
    }
}