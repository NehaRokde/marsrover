package com.example.listellanasaapp.ui.marsrover.curosity

import android.util.Log
import android.widget.ImageView
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.example.listellanasaapp.data.api.model.Photo
import com.example.listellanasaapp.ui.marsrover.MarsRoverViewModel
import com.example.listellanasaapp.ui.recentphotos.LazyVerticalGridView
import com.example.listellanasaapp.util.Constants

@Composable
fun CuriosityScreen(viewModel: MarsRoverViewModel = hiltViewModel()) {

    val query: MutableState<String> = remember {
        mutableStateOf("")
    }
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