package com.example.listellanasaapp.ui.marsrover

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.listellanasaapp.data.api.model.Photo
import com.example.listellanasaapp.ui.marsrover.curosity.ImageView
import com.example.listellanasaapp.util.Constants

@Composable
fun OpportunityScreen(viewModel: MarsRoverViewModel = hiltViewModel()){

    val query: MutableState<String> = remember {
        mutableStateOf("")
    }
    val result = viewModel.list.value

    LaunchedEffect(Unit, block = {
        viewModel.getMarsRoverPhotos(Constants.OPPORTUNITY, Constants.CAMERA_PANCAM)
    })
    Log.d("TAG", "ashish: $result")
    Surface(modifier = Modifier.fillMaxSize()) {
        if (result.isLoading) {
            Log.d("TAG", "MainContent: in the loading")
            Box(modifier = Modifier.fillMaxSize().background(Color.Blue)) {
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
                ImageView(photoList)
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "No Data Found", modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }

}

@Composable
fun ImageView(photo: List<Photo>) {
    val imagerPainter = rememberAsyncImagePainter(model = photo.get(0).imgSrc)

    Card(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = imagerPainter,
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }


}