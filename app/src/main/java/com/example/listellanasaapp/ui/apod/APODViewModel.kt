package com.example.listellanasaapp.ui.apod

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listellanasaapp.data.api.model.APODResponse
import com.example.listellanasaapp.repository.NasaRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class APODViewModel @Inject constructor(private val nasaRepo: NasaRepo) : ViewModel() {

    private val _state = MutableStateFlow<APODResponse?>(null)
    val state: StateFlow<APODResponse?>
        get() = _state

    init {
        viewModelScope.launch {
            val apod = nasaRepo.getAPOD()
            _state.value = apod
        }
    }
}