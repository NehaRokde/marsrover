package com.example.listellanasaapp.ui.marsrover.curosity

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.listellanasaapp.ui.marsrover.ImageView
import com.example.listellanasaapp.ui.marsrover.MarsRoverViewModel
import com.example.listellanasaapp.data.util.Constants

@Composable
fun CuriosityScreen(viewModel: MarsRoverViewModel = hiltViewModel()) {

    val result = viewModel.list.value

    LaunchedEffect(Unit, block = {
        viewModel.getMarsRoverPhotos(Constants.CURIOSITY, Constants.CAMERA_FHAZ)
    })

    Surface(modifier = Modifier.fillMaxSize()) {
        if (result.isLoading) {
            Log.d("TAG", "MainContent: in the loading")
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        if (result.error.isNotBlank()) {
            Log.d("TAG", "MainContent: ${result.error}")
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = viewModel.list.value.error, modifier = Modifier.align(Alignment.Center))
            }
        }

        if (result.data.isNotEmpty()) {
            Log.d("TAG", "MainContent: ${result.data.size}")
            val photoList = viewModel.list.value.data
            if (photoList.isNotEmpty()) {
                ImageView(photo = photoList)
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "No Data Found", modifier = Modifier.align(Alignment.Center))
                }
            }
        }

    }

}

