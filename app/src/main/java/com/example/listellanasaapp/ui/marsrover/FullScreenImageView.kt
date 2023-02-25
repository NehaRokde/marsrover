package com.example.listellanasaapp.ui.marsrover

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import coil.compose.rememberAsyncImagePainter
import com.example.listellanasaapp.data.model.Photo

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