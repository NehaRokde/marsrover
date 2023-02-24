package com.example.listellanasaapp.ui.apod

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.listellanasaapp.data.api.model.APODResponse

@Composable
fun APODScreen() {
    val viewModel: APODViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()


    APODPhotoOfTheDayView(state)

}

@Composable
fun APODPhotoOfTheDayView(apodResponse: APODResponse?) {
    val imagerPainter = rememberAsyncImagePainter(model = apodResponse?.hdurl)

    Column() {
        Image(
            painter = imagerPainter,
            contentDescription = null,
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.Fit
        )

        Row(
            modifier = Modifier
                .padding(15.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = apodResponse?.title.toString(),
                style = MaterialTheme.typography.h3,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp

            )
            Text(
                text = apodResponse?.date.toString(),
                style = MaterialTheme.typography.h4,
                color = Color.White,
                fontSize = 16.sp
            )
        }

    }


}