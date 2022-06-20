package com.example.rickmorty.presentation.ui.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.model.ApiResponse
import com.example.rickmorty.data.model.Episode
import com.example.rickmorty.domain.interactor.GetEpisodeUseCase
import kotlinx.coroutines.launch

class EpisodeViewModel(
    private val getEpisodeUseCase: GetEpisodeUseCase
) : ViewModel() {
    private val _episodeList = MutableLiveData<ApiResponse<Episode>>()
    val episodeList: LiveData<ApiResponse<Episode>> = _episodeList

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
                _episodeList.postValue(getEpisodeUseCase.execute())
                _searchList.postValue(getEpisodeUseCase.execute().results)
            } catch (e: Throwable) {
                _action.value = Action.ShowError("Нестабильное соединение")
            }
            _action.value = Action.HideLoader
        }
    }

    fun searchCharacter(inputText: String) {
        val resList = mutableListOf<Episode>()
        inputText.let {
            _episodeList.value?.results?.forEach { item ->
                if (item.name.lowercase().contains(it))
                    resList.add(item)
            }
            _searchList.value = resList
        }
    }

    fun transactionAllData(){
        _searchList.value = episodeList.value?.results
    }


    sealed class Action {
        object HideLoader : Action()
        data class ShowError(val errorMessage: String) : Action()
    }
}