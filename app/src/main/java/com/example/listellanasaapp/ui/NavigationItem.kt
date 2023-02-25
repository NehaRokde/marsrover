package com.example.listellanasaapp.ui

import com.example.listellanasaapp.R


sealed class NavigationItem(
    var route: String,
    var iconSelected: Int,
    var iconUnselected: Int
) {
    object APOD : NavigationItem("apod", R.drawable.apod_nav_active, R.drawable.apod_nav)
    object MarsRover :
        NavigationItem("marsrover", R.drawable.mars_rover_nav_active, R.drawable.mars_rover_nav)

    object RecentPhotos : NavigationItem(
        "recentphotos",
        R.drawable.recent_photos_nav_active,
        R.drawable.recent_photos_nav
    )

    object Menu : NavigationItem("menu", R.drawable.menu_nav, R.drawable.menu_nav)

}
