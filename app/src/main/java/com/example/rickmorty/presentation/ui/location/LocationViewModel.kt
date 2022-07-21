package com.example.rickmorty.presentation.ui.location

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.models.location.LocationModel
import com.example.rickmorty.presentation.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val interactor: LocationInteractor
) : BaseViewModel() {

    private var locationModelList = listOf<LocationModel>()

    private val _searchList = MutableLiveData<List<LocationModel>>()
    val searchList: LiveData<List<LocationModel>> = _searchList

    private val _action = MutableLiveData<Action>()
    val action: LiveData<Action> = _action

    init {
        load()
    }

    fun load() {
//        load(_searchList){
//            interactor.getLocationList()
//        }
        viewModelScope.launch {
            try {
                _searchList.postValue(interactor.getLocationList())
                locationModelList = interactor.getLocationList()
            } catch (e: Throwable) {
                _action.value = Action.ShowError("Нестабильное соединение")
            }
            _action.value = Action.HideLoader
        }
    }

    fun searchLocation(inputText: String) {
        if (inputText.isEmpty()){
            _searchList.postValue(locationModelList)
        }else{
            _searchList.value = locationModelList.filter {
                it.name.lowercase().contains(inputText.lowercase())
            }
        }
    }

//    fun transactionAllData(){
//        _searchList.value = locationModelList
//    }


    sealed class Action {
        object HideLoader : Action()
        data class ShowError(val errorMessage: String) : Action()
    }
}