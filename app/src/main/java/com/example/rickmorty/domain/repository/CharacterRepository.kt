package com.example.rickmorty.domain.repository

import com.example.rickmorty.data.models.character.Character


interface CharacterRepository {
    suspend fun getCharacters(): List<Character>
}