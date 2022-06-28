package com.example.rickmorty.domain.interactor

import com.example.rickmorty.data.models.character.Character
import com.example.rickmorty.domain.repository.CharacterRepository

class GetCharactersUseCase(
    private val repository: CharacterRepository
) {
    suspend fun execute(): List<Character> = repository.getCharacters()
}