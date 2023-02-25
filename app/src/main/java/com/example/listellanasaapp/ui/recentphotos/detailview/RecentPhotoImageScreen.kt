package com.example.listellanasaapp.ui.recentphotos.detailview

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecentPhotoFullImage(
    navController: NavController, imageUrl: String?
) {
    Box(
        modifier = Modifier.fillMaxSize(),
    ) {
        Log.i("MyTag", "imageUrl : $imageUrl")
        val imagerPainter = rememberAsyncImagePainter(model = imageUrl)
        Image(
            painter = imagerPainter,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}
