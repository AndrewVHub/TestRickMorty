package com.example.rickmorty.presentation.ui.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.models.episode.Episode
import com.example.rickmorty.domain.interactor.GetEpisodeUseCase
import kotlinx.coroutines.launch

class EpisodeViewModel(
    private val getEpisodeUseCase: GetEpisodeUseCase
) : ViewModel() {

    private var episodeList = listOf<Episode>()

    private val _searchList = MutableLiveData<List<Episode>>()
    val searchList: LiveData<List<Episode>> = _searchList

    private val _action = MutableLiveData<Action>()
    val action: LiveData<Action> = _action

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            try {
                _searchList.postValue(getEpisodeUseCase.execute())
                episodeList = getEpisodeUseCase.execute()
            } catch (e: Throwable) {
                _action.value = Action.ShowError("Нестабильное соединение")
            }
            _action.value = Action.HideLoader
        }
    }

    fun searchEpisode(inputText: String) {
        if (inputText.isEmpty()){
            _searchList.postValue(episodeList)
        }else{
            _searchList.value = episodeList.filter {
                it.name.lowercase().contains(inputText)
            }
        }
    }

    fun transactionAllData(){
        _searchList.value = episodeList
    }


    sealed class Action {
        object HideLoader : Action()
        data class ShowError(val errorMessage: String) : Action()
    }
}