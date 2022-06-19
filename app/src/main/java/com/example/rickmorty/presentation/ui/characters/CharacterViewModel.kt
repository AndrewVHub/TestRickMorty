package com.example.rickmorty.presentation.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.model.Character
import com.example.rickmorty.data.model.CharactersList
import com.example.rickmorty.domain.interactor.GetCharactersUseCase
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    private val _characterList = MutableLiveData<CharactersList>()
    val characterList: LiveData<CharactersList> = _characterList

    private val _searchList = MutableLiveData<List<Character>>()
    val searchList: LiveData<List<Character>> = _searchList

    private val _action = MutableLiveData<CharacterAction>()
    val action: LiveData<CharacterAction> = _action

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            try {
                _characterList.postValue(getCharactersUseCase.execute())
                _searchList.postValue(getCharactersUseCase.execute().results)
            } catch (e: Throwable) {
                _action.value = CharacterAction.ShowError("Нестабильное соединение")
            }
            _action.value = CharacterAction.HideLoader
        }
    }

    fun searchCharacter(inputText: String) {
        val resList = mutableListOf<Character>()
        inputText.let {
            _characterList.value?.results?.forEach { item ->
                if (item.name.lowercase().contains(it))
                    resList.add(item)
            }
            _searchList.value = resList
        }
    }

    fun transactionAllData(){
        _searchList.value = characterList.value?.results
    }


    sealed class CharacterAction {
        object HideLoader : CharacterAction()
        data class ShowError(val errorMessage: String) : CharacterAction()
    }
}