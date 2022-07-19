package com.example.rickmorty.presentation.ui.multiple

import com.example.rickmorty.data.models.location.LocationModel

sealed class MultipleRecyclerViewItem{}

class Location(
    val locationModel: LocationModel
) : MultipleRecyclerViewItem()

class NoLocation(
    val string: String
) : MultipleRecyclerViewItem()