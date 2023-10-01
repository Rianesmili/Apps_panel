package com.example.apppan.feature.actualites

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apppan.data.Actu
import com.example.apppan.repository.ActualitesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ActualitesViewModel @Inject constructor() : ViewModel() {
    private val repository = ActualitesRepository()

    val actualitesLiveData: MutableLiveData<List<Actu>?> = repository.actualitesLiveData
    val loadingState: MutableLiveData<LoadingState> = MutableLiveData(LoadingState.LOADING)

    sealed class LoadingState {
        object LOADING : LoadingState()
        object LOADED : LoadingState()
    }

    init {
        viewModelScope.launch {
            try {
                loadingState.value = LoadingState.LOADING
                repository.fetchActualites()
                loadingState.value = LoadingState.LOADED
            } catch (e: Exception) {
                Log.d("", "hello from erreur dans actualiteviewmodel")
            }
            loadingState.postValue(LoadingState.LOADED)
        }
    }
}

