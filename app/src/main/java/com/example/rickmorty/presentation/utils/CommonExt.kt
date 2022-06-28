package com.example.rickmorty.presentation.utils

import android.widget.ImageView
import com.example.rickmorty.data.models.character.CharacterResponse
import com.example.rickmorty.data.models.episode.Episode
import com.example.rickmorty.data.models.episode.EpisodeResponse
import com.example.rickmorty.data.models.location.Location
import com.example.rickmorty.data.models.location.LocationResponse
import com.example.rickmorty.data.models.character.Character
import com.squareup.picasso.Picasso

fun ImageView.load(url: String, error: Int? = null) {
    if (error == null)
        Picasso.get().load(url).into(this)
    else Picasso.get().load(url).error(error).into(this)
}

fun EpisodeResponse.toModel(): Episode {
    return Episode(
        id, name, airDate, episode
    )
}

fun CharacterResponse.toModel(): Character{
    return Character(
        id, name, species, gender, location, image
    )
}

fun LocationResponse.toModel(): Location {
    return Location(
        id, name, type, dimension
    )
}

