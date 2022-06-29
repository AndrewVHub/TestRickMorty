package com.example.rickmorty.presentation.ui.characters

import com.example.rickmorty.data.network.ApiWorker
import com.example.rickmorty.data.models.character.CharacterModel
import com.example.rickmorty.presentation.mapper.CharacterMapper

class CharacterInteractor(
    private val apiWorker: ApiWorker
) {
    suspend fun getCharacterList(): List<CharacterModel> = apiWorker.getCharacters().map {
        CharacterMapper.map(it)
    }
}