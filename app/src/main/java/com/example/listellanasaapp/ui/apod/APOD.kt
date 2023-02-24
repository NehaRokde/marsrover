package com.example.listellanasaapp.ui.apod

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImagePainter.State.Empty.painter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.listellanasaapp.data.api.model.APODResponse
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Preview
@Composable
fun APODScreen() {
    val viewModel: APODViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()


//    APODPhotoOfTheDayView(state)
    ImageAndText(state)

}

@Composable
fun ImageAndText(
    apodResponse: APODResponse?
) {
    val imagerPainter = rememberAsyncImagePainter(model = apodResponse?.hdurl)
    val shape = RoundedCornerShape(8.dp)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(White, shape = shape),
        contentAlignment = Alignment.BottomCenter,

        ) {

        Image(
            painter = imagerPainter,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape)
        )

        Row(
            Modifier
                .height(56.dp)
                .fillMaxWidth().background(color = Color.Black),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.alpha(0.9f).padding(10.dp),
                text = apodResponse?.title.toString(),
                style = TextStyle(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 20.sp
                    ),
                color = White
            )
            Spacer(Modifier.weight(1f))
            Text(
                modifier = Modifier.alpha(0.9f).padding(10.dp),
                text = apodResponse?.date.toString(),
                style = MaterialTheme.typography.h4,
                color = White,
                fontSize = 20.sp
            )
        }

    }

}