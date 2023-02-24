package com.example.listellanasaapp.ui.recentphotos

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.listellanasaapp.R
import com.example.listellanasaapp.data.api.model.Photo
import com.example.listellanasaapp.ui.marsrover.MarsRoverViewModel
import com.example.listellanasaapp.ui.theme.ListellaNASAAppTheme
import com.example.listellanasaapp.util.Constants

@Composable
fun RecentPhotosScreen(viewModel: MarsRoverViewModel = hiltViewModel()) {

    val query: MutableState<String> = remember { mutableStateOf("") }
    val result = viewModel.list.value

    LaunchedEffect(Unit, block = {
        viewModel.getMarsRoverPhotos(Constants.CURIOSITY, Constants.CAMERA_MAST)
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
                LazyVerticalGridView(photoList)
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(text = "No Data Found", modifier = Modifier.align(Alignment.Center))
                }
            }
        }

    }
}

@Composable
fun LazyVerticalGridView(photo: List<Photo>) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        // content padding
        contentPadding = PaddingValues(8.dp),
        content = {
            items(photo.size) { index ->
                Card(
                    modifier = Modifier
                        .padding(6.dp)
                        .width(140.dp)
                        .height(180.dp),
                    elevation = 3.dp,
                    shape = RoundedCornerShape(10.dp)

                ) {
                    val imagerPainter = rememberAsyncImagePainter(model = photo.get(index).imgSrc)
                    Log.d("TAG", "imagerPainter: ${photo.get(index).imgSrc}")

                    val localImage = rememberImagePainter(data = R.drawable.pexels)
                    Image(
                        painter = imagerPainter,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ListellaNASAAppTheme {
        RecentPhotosScreen()
    }
}