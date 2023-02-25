package com.example.listellanasaapp.ui.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MenuScreen() {

    val items = listOf(
        MenuItem.Account,
        MenuItem.Search,
        MenuItem.APOD,
        MenuItem.MarsRover,
        MenuItem.StarTracker,
        MenuItem.RecentPhotos,
        MenuItem.Settings
    )

    Column {
        val drawerState = rememberBottomDrawerState(BottomDrawerValue.Open)
        BottomDrawer(
            drawerState = drawerState,
            drawerContent = {

                LazyColumn() {
                    items(items.size) {
                        Divider(color = Color.LightGray, thickness = 0.8.dp)
                        ListItem(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.2F),
                            text = { Text("${items[it].route}") },
                            icon = {
                                Icon(
                                    painterResource(id = items[it].icon),
                                    contentDescription = null,
                                    Modifier.size(40.dp)
                                )
                            },
                        )
                    }
                }
            },
            content = {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                }
            }
        )
    }
}
