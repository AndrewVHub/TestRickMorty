package com.example.rickmorty.data.datasource

import com.example.rickmorty.data.datasource.network.AppService
import com.example.rickmorty.data.model.Character
import com.example.rickmorty.data.model.CharactersList

class RemoteDataSource(
    private val service: AppService
) {
    suspend fun getCharacters(): CharactersList = service.getCharacters()
}