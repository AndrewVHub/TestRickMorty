package com.example.rickmorty.domain.interactor

import com.example.rickmorty.data.model.Character
import com.example.rickmorty.data.model.ApiResponse
import com.example.rickmorty.domain.repository.CharacterRepository

class GetCharactersUseCase(
    private val repository: CharacterRepository
) {
    suspend fun execute(): ApiResponse<Character> = repository.getCharacters()
}