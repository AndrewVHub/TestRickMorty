package com.example.rickmorty.domain.interactor

import com.example.rickmorty.data.model.Character
import com.example.rickmorty.data.model.CharactersList
import com.example.rickmorty.domain.repository.CharacterRepository

class GetCharactersUseCase(
    private val repository: CharacterRepository
) {
    suspend fun execute(): CharactersList = repository.getCharacters()
}