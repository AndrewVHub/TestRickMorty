package com.example.rickmorty.presentation.ui.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.constants.ConstantKeys
import com.example.rickmorty.data.models.character.Character
import com.example.rickmorty.domain.interactor.GetCharactersUseCase
import kotlinx.coroutines.launch

class CharacterViewModel(
    private val getCharactersUseCase: GetCharactersUseCase
) : ViewModel() {
    private var characterList = listOf<Character>()

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
                characterList = getCharactersUseCase.execute()
                _searchList.postValue(characterList)
            } catch (e: Throwable) {
                _action.value = CharacterAction.ShowError(ConstantKeys.LOST_INTERNET)
            }
            _action.value = CharacterAction.HideLoader
        }
    }

    fun searchCharacter(inputText: String) {
        if (inputText.isEmpty()){
            _searchList.postValue(characterList)
        }else{
            _searchList.value = characterList.filter {
                it.name.lowercase().contains(inputText)
            }
        }
    }

    //Если сделаю удаление (крестик в EditText)
    fun transactionAllData(){
        _searchList.value = characterList
    }


    sealed class CharacterAction {
        object HideLoader : CharacterAction()
        data class ShowError(val errorMessage: String) : CharacterAction()
    }
}