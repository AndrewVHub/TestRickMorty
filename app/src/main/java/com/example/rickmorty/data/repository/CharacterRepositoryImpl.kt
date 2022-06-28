package com.example.rickmorty.data.repository

import com.example.rickmorty.data.datasource.RemoteDataSource
import com.example.rickmorty.data.models.character.Character
import com.example.rickmorty.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val dataSource: RemoteDataSource
): CharacterRepository {
    override suspend fun getCharacters(): List<Character> = dataSource.getCharacters()
}