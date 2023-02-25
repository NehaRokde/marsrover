package com.example.listellanasaapp.ui.recentphotos

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.listellanasaapp.ui.recentphotos.detailview.RecentPhotoFullImage
import com.example.listellanasaapp.ui.theme.ListellaNASAAppTheme

@Composable
fun RecentPhotosScreen(viewModel: RecentPhotosViewModel = hiltViewModel()) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {
        val navController = rememberNavController()

        NavHost(
            navController,
            startDestination = "photo_gallery",
        ) {

            //First_screen = photo_gallery
            composable("photo_gallery") {
                RecentPhotoScreen(navController = navController)
            }

            //second_screen = full_image
            composable(
                "full_image/{imageurl}",
                arguments = listOf(navArgument("imageurl") {
                    type = NavType.StringType
                })
            ) {
                RecentPhotoFullImage(
                    navController = navController,
                    it.arguments?.getString("imageurl")

                )
            }

        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ListellaNASAAppTheme {
        RecentPhotosScreen()
    }
}