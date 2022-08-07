package com.example.rickmorty.presentation.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmorty.data.models.character.CharacterModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {


    fun <T> load(liveData: MutableLiveData<List<T>>, doOnAsync: suspend () -> List<T>) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                liveData.postValue(doOnAsync.invoke())
            } catch (e: Throwable) {
            }
        }
    }
}