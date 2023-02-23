package com.example.listellademoapp.presentation.ui.view

import com.example.listellanasaapp.R


sealed class NavigationItem(
    var route: String,
    var icon: Int,
    var title: String
) {
    object APOD : NavigationItem("apod", R.drawable.apod, "APOD")
    object MarsRover : NavigationItem("marsrover", R.drawable.mars_rover, "MarsRover")
    object RecentPhotos : NavigationItem("recentphotos", R.drawable.recent_photos, "RecentPhoto")
    object Menu : NavigationItem("menu", R.drawable.baseline_menu_24, "Menu")

}
