package com.example.listellanasaapp.ui.recentphotos

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listellanasaapp.domain.repository.NasaRepository
import com.example.listellanasaapp.ui.marsrover.MainState
import com.example.listellanasaapp.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentPhotosViewModel @Inject constructor(
    private val nasaRepository: NasaRepository
) : ViewModel() {


    val list: MutableState<RecentPhotosMainState> = mutableStateOf(RecentPhotosMainState())

    fun getRecentPhotos(count: Int) = viewModelScope.launch {
        list.value = RecentPhotosMainState(isLoading = true)
        try {
            when (val result = nasaRepository.getRecentPhotos(count)) {
                is Resource.Loading -> {
                    list.value = RecentPhotosMainState(isLoading = false)
                }
                is Resource.Success -> {
                    result.data?.let {
                        list.value = RecentPhotosMainState(data = it)
                    }
                }
                is Resource.Error -> {
                    list.value = RecentPhotosMainState(error = "Something Went Wrong")
                }

            }
        } catch (e: Exception) {
            list.value = RecentPhotosMainState(error = "Something went wrong")
        }
    }


}