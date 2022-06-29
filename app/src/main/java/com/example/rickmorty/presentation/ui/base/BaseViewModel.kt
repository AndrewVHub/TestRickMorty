package com.example.rickmorty.presentation.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {

    fun <T> load(liveData: MutableLiveData<List<T>>, doOnAsync: suspend () -> List<T>) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                liveData.value = doOnAsync.invoke()
            } catch (e: Throwable) {
            }
        }
    }
}