package com.example.rickmorty.presentation.ui.episode

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.models.episode.EpisodeModel
import com.example.rickmorty.presentation.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class EpisodeViewModel(
    private val interactor: EpisodeInteractor
) : BaseViewModel() {

    private var episodeModelList = listOf<EpisodeModel>()

    private val _searchList = MutableLiveData<List<EpisodeModel>>()
    val searchList: LiveData<List<EpisodeModel>> = _searchList

    private val _action = MutableLiveData<Action>()
    val action: LiveData<Action> = _action

    init {
        load()
    }

    fun load() {

//        load(_searchList){
//            interactor.getEpisodeList()
//        }
        viewModelScope.launch {
            try {
                _searchList.postValue(interactor.getEpisodeList())
                episodeModelList = interactor.getEpisodeList()
            } catch (e: Throwable) {
                _action.value = Action.ShowError("Нестабильное соединение")
            }
            _action.value = Action.HideLoader
        }
    }

    fun searchEpisode(inputText: String) {
        if (inputText.isEmpty()){
            _searchList.postValue(episodeModelList)
        }else{
            _searchList.value = episodeModelList.filter {
                it.name.lowercase().contains(inputText.lowercase())
            }
        }
    }

//    fun transactionAllData(){
//        _searchList.value = episodeModelList
//    }


    sealed class Action {
        object HideLoader : Action()
        data class ShowError(val errorMessage: String) : Action()
    }
}