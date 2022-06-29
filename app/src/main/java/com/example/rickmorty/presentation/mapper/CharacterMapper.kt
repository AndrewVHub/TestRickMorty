package com.example.rickmorty.presentation.mapper

import com.example.rickmorty.data.models.character.CharacterModel
import com.example.rickmorty.data.models.character.CharacterResponse
import com.example.rickmorty.presentation.mapper.base.Mapper

object CharacterMapper: Mapper<CharacterResponse, CharacterModel>() {
    override fun map(from: CharacterResponse): CharacterModel = from.run {
        CharacterModel(
            id, name, species, gender, location, image
        )
    }
}