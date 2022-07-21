package com.example.rickmorty.presentation.ui.multiple

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.models.location.LocationModel
import com.example.rickmorty.presentation.ui.base.BaseViewModel
import com.example.rickmorty.presentation.ui.location.LocationInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MultipleViewModel  @Inject constructor(
    private val interactor: LocationInteractor
) : BaseViewModel() {

    private var locationModelList = listOf<LocationModel>()

    private val _searchList = MutableLiveData<List<MultipleRecyclerViewItem>>()
    val searchList: LiveData<List<MultipleRecyclerViewItem>> = _searchList

    init {
        load()
    }

    fun load() {

        viewModelScope.launch {
            try {
                locationModelList = interactor.getLocationList()
                val openList = mutableListOf<MultipleRecyclerViewItem>()
                locationModelList.map {
                    openList.add(
                        Location(
                            locationModel = it
                        )
                    )
                    openList.add(NoLocation(string = "Ну, тут второй холдер"))
                }
                _searchList.postValue(openList)


            } catch (e: Throwable) {

            }
        }
    }


}