package com.example.listellanasaapp.ui.apod

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun APODScreen() {
    val viewModel: APODViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()
    val imagerPainter = rememberAsyncImagePainter(model = state?.hdurl)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(4.dp)
    ) {

        Image(
            painter = imagerPainter,
            contentDescription = null,
            modifier = Modifier
                .fillMaxSize(),
            contentScale = ContentScale.Fit


        )
    }

}