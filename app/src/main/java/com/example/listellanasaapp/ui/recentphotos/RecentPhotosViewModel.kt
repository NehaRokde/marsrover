package com.example.listellanasaapp.ui.recentphotos

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.listellanasaapp.data.model.APODResponse
import com.example.listellanasaapp.domain.repository.NasaRepository
import com.example.listellanasaapp.ui.marsrover.MainState
import com.example.listellanasaapp.data.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentPhotosViewModel @Inject constructor(
    private val nasaRepository: NasaRepository
) : ViewModel() {


    val list1= MutableStateFlow<RecentPhotosMainState?>(null)


    init {
        viewModelScope.launch(Dispatchers.IO) {
            getRecentPhotos(50)
        }
    }


    private suspend fun getRecentPhotos(count: Int) {
//       var list : RecentPhotosMainState
//        list = RecentPhotosMainState(isLoading = true)
        list1.value =RecentPhotosMainState(isLoading = true)
        when (val result = nasaRepository.getRecentPhotos(count)) {
            is Resource.Loading -> {
              //  list = RecentPhotosMainState(isLoading = false)
                list1.value =RecentPhotosMainState(isLoading = false)

            }
            is Resource.Success -> {
                result.data?.let {
                    list1.value = RecentPhotosMainState(data = it)
                }
            }
            is Resource.Error -> {
                list1.value = RecentPhotosMainState(error = "Something Went Wrong")
            }

        }

    }

}