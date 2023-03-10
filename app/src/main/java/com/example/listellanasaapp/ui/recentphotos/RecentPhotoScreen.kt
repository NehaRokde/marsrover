package com.example.listellanasaapp.ui.recentphotos

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.listellanasaapp.data.model.RecentPhotosResponseItem
import com.example.listellanasaapp.ui.apod.APODViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets


//@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RecentPhotoScreen(navController: NavController) {

    val viewModel: RecentPhotosViewModel = hiltViewModel()
    val result by viewModel.list1.collectAsState()

    if (result != null) {
        if (result!!.isLoading) {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
        if (result!!.error.isNotBlank()) {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = result!!.error, modifier = Modifier.align(Alignment.Center))
            }
        }
        if (result!!.data.isNotEmpty()) {
            val photoList = result!!.data
            ImageGallery(
                photo = photoList,
                navController = navController
            )
        }
    }


}

@Composable
fun ImageGallery(
    photo: List<RecentPhotosResponseItem>,
    navController: NavController
) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
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
                    if (photo.isNotEmpty() && photo[index].hdurl != null) {
                        val imagerPainter = rememberAsyncImagePainter(model = photo[index].hdurl)
                        val encodedUrl = URLEncoder.encode(
                            photo.get(index).hdurl,
                            StandardCharsets.UTF_8.toString()
                        )

                        Image(
                            painter = imagerPainter,
                            contentDescription = "",
                            modifier = Modifier
                                .fillMaxSize()
                                .clickable {
                                    navController.navigate("full_image/$encodedUrl")
                                },
                            contentScale = ContentScale.Crop,
                        )
                    }
                }
            }
        }
    )
}