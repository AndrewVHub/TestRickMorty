package com.example.rickmorty.presentation.mapper

import com.example.rickmorty.data.models.character.Character
import com.example.rickmorty.data.models.character.CharacterResponse
import com.example.rickmorty.domain.mapper.Mapper

class CharacterMapper(): Mapper<CharacterResponse, Character>() {
    override fun map(from: CharacterResponse): Character = from.run {
        Character(
            id, name, species, gender, location, image
        )
    }

}