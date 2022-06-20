package com.example.rickmorty.domain.repository

import com.example.rickmorty.data.model.Character
import com.example.rickmorty.data.model.ApiResponse

interface CharacterRepository {
    suspend fun getCharacters(): ApiResponse<Character>
}