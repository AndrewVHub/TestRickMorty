package com.example.rickmorty.presentation.ui.characters

import android.app.DownloadManager
import android.content.Context
import android.location.LocationManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemServiceName
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.constants.ConstantKeys
import com.example.rickmorty.data.models.character.CharacterModel
import com.example.rickmorty.data.network.ApiWorker
import com.example.rickmorty.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    private val interactor: CharacterInteractor
) : BaseViewModel() {
    private var characterModelList = emptyList<CharacterModel>()

    private val _searchList = MutableLiveData<List<CharacterModel>>()
    val searchList: LiveData<List<CharacterModel>> = _searchList

    private val _action = MutableLiveData<CharacterAction>()
    val action: LiveData<CharacterAction> = _action

    init {
        load()
    }
    fun load() {
//        load(_searchList){
//            interactor.getCharacterList()
//        }
        viewModelScope.launch {
            try {
                characterModelList = interactor.getCharacterList()
                _searchList.postValue(characterModelList)
            } catch (e: Throwable) {
                _action.value = CharacterAction.ShowError(ConstantKeys.LOST_INTERNET)
            }
            _action.value = CharacterAction.HideLoader
        }
    }



    fun searchCharacter(inputText: String) {
        if (inputText.isEmpty()){
            _searchList.postValue(characterModelList)
        }else{
            _searchList.value = characterModelList.filter {
                it.name.lowercase().contains(inputText.lowercase())
            }
        }
    }

    //Если сделаю удаление (крестик в EditText)
//    fun transactionAllData(){
//        _searchList.value = characterModelList
//    }


    sealed class CharacterAction {
        object HideLoader : CharacterAction()
        data class ShowError(val errorMessage: String) : CharacterAction()
    }
}