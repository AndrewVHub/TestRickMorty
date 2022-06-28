package com.example.rickmorty.presentation.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.models.ApiResponse
import com.example.rickmorty.data.models.location.Location
import com.example.rickmorty.domain.interactor.GetLocationUseCase
import kotlinx.coroutines.launch

class LocationViewModel (
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {

    private var locationList = listOf<Location>()

    private val _searchList = MutableLiveData<List<Location>>()
    val searchList: LiveData<List<Location>> = _searchList

    private val _action = MutableLiveData<Action>()
    val action: LiveData<Action> = _action

    init {
        load()
    }

    fun load() {
        viewModelScope.launch {
            try {
                _searchList.postValue(getLocationUseCase.execute())
                locationList = getLocationUseCase.execute()
            } catch (e: Throwable) {
                _action.value = Action.ShowError("Нестабильное соединение")
            }
            _action.value = Action.HideLoader
        }
    }

    fun searchLocation(inputText: String) {
        if (inputText.isEmpty()){
            _searchList.postValue(locationList)
        }else{
            _searchList.value = locationList.filter {
                it.name.lowercase().contains(inputText)
            }
        }
    }

    fun transactionAllData(){
        _searchList.value = locationList
    }


    sealed class Action {
        object HideLoader : Action()
        data class ShowError(val errorMessage: String) : Action()
    }
}