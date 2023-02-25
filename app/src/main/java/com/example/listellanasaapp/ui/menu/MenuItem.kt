package com.example.listellanasaapp.ui.menu

import com.example.listellanasaapp.R

sealed class MenuItem(
    var route: String,
    var icon: Int
) {
    object Account : MenuItem("Account", R.drawable.account_menu)
    object Search : MenuItem("Search", R.drawable.search_menu)
    object APOD : MenuItem("APOD", R.drawable.apod_menu)
    object MarsRover : MenuItem("MarsRover", R.drawable.mars_rover_menu)
    object StarTracker : MenuItem("StarTracker", R.drawable.star_tracker_menu)
    object RecentPhotos : MenuItem("RecentPhotos", R.drawable.recent_photos_menu)
    object Settings : MenuItem("Settings", R.drawable.settings_menu)

}
