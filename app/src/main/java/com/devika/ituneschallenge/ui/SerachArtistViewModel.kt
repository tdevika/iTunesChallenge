package com.devika.ituneschallenge.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devika.ituneschallenge.data.domain.UiState
import com.devika.ituneschallenge.utils.Results
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

class SerachArtistViewModel @Inject constructor(private val searchArtistUseCase: SearchArtistUseCase) :
    ViewModel() {
    val uiState = MutableLiveData<UiState>()

     fun getArtistData(artist: String) {
        viewModelScope.launch {
            searchArtistUseCase.getArtistData(artist).collectLatest {
                when (it) {
                    is Results.Success -> uiState.value = UiState.Success(it.value)
                    is Results.Error -> uiState.value = UiState.Error(it.message)
                    is Results.Progress -> uiState.value = UiState.Progress
                }
            }
        }
    }
}