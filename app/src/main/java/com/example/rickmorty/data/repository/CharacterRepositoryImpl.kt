package com.example.rickmorty.data.repository

import com.example.rickmorty.data.datasource.RemoteDataSource
import com.example.rickmorty.data.model.Character
import com.example.rickmorty.data.model.ApiResponse
import com.example.rickmorty.domain.repository.CharacterRepository

class CharacterRepositoryImpl(
    private val dataSource: RemoteDataSource
): CharacterRepository {
    override suspend fun getCharacters(): ApiResponse<Character> = dataSource.getCharacters()
}