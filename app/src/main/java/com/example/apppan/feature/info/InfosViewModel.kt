package com.example.apppan.feature.info

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apppan.repository.InfosRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InfosViewModel @Inject constructor(private val repository : InfosRepository) : ViewModel() {


    val webViewUrl: LiveData<String> = repository.getWebViewUrl()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchWebViewUrl()
        }
    }
}