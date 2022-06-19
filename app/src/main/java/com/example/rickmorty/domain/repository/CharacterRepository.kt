package com.example.rickmorty.domain.repository

import com.example.rickmorty.data.model.Character
import com.example.rickmorty.data.model.CharactersList

interface CharacterRepository {
    suspend fun getCharacters(): CharactersList
}