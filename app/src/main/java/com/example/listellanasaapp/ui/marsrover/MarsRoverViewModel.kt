package com.example.listellanasaapp.ui.marsrover

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listellanasaapp.data.model.MarsRoverPhotosResponse
import com.example.listellanasaapp.data.model.Photo
import com.example.listellanasaapp.domain.repository.NasaRepository
import com.example.listellanasaapp.data.util.Constants
import com.example.listellanasaapp.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MarsRoverViewModel @Inject constructor(
    private val nasaRepository: NasaRepository
) : ViewModel() {


    val list: MutableState<MainState> = mutableStateOf(MainState())

    fun getMarsRoverPhotos(roverName: String, camera: String) = viewModelScope.launch {
        list.value = MainState(isLoading = true)
        try {
            when (val result = nasaRepository.getMarsRoverPhotos(roverName, camera)) {
                is Resource.Loading -> {
                    list.value = MainState(isLoading = false)
                }
                is Resource.Success -> {
                    result.data?.photos?.let {
                        list.value = MainState(data = it)
                    }
                }
                is Resource.Error -> {
                    list.value = MainState(error = "Something Went Wrong")
                }

            }
        } catch (e: Exception) {
            list.value = MainState(error = "Something went wrong")
        }
    }


}