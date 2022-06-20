package com.example.rickmorty.presentation.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.model.ApiResponse
import com.example.rickmorty.data.model.Location
import com.example.rickmorty.domain.interactor.GetLocationUseCase
import kotlinx.coroutines.launch

class LocationViewModel (
    private val getLocationUseCase: GetLocationUseCase
) : ViewModel() {
    private val _locationList = MutableLiveData<ApiResponse<Location>>()
    val locationList: LiveData<ApiResponse<Location>> = _locationList

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
                _locationList.postValue(getLocationUseCase.execute())
                _searchList.postValue(getLocationUseCase.execute().results)
            } catch (e: Throwable) {
                _action.value = Action.ShowError("Нестабильное соединение")
            }
            _action.value = Action.HideLoader
        }
    }

    fun searchLocation(inputText: String) {
        val resList = mutableListOf<Location>()
        inputText.let {
            _locationList.value?.results?.forEach { item ->
                if (item.name.lowercase().contains(it))
                    resList.add(item)
            }
            _searchList.value = resList
        }
    }

    fun transactionAllData(){
        _searchList.value = locationList.value?.results
    }


    sealed class Action {
        object HideLoader : Action()
        data class ShowError(val errorMessage: String) : Action()
    }
}