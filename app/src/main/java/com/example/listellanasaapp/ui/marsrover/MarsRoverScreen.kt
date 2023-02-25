package com.example.listellanasaapp.ui.marsrover

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.listellanasaapp.ui.marsrover.curosity.CuriosityScreen
import com.example.listellanasaapp.ui.marsrover.opportunity.OpportunityScreen
import com.example.listellanasaapp.ui.marsrover.spirit.SpiritScreen

@Composable
fun MarsRoverScreen() {

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(modifier = Modifier.padding(padding)) {
            TabBar()
        }
    }
}


@Composable
fun TabBar() {
    var tabIndex by remember { mutableStateOf(0) }
    val tabTitles = listOf("Curiosity", "Opportunity", "Spirit")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
    ) {
        TabRow(
            selectedTabIndex = tabIndex,
            backgroundColor = Color.Black,
            contentColor = Color.White
        ) {
            tabTitles.forEachIndexed { index, title ->
                Tab(selected = tabIndex == index,
                    onClick = { tabIndex = index },
                    text = { Text(text = title) })
            }
        }
        when (tabIndex) {
            0 -> CuriosityScreen()
            1 -> OpportunityScreen()
            2 -> SpiritScreen()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MarsRoverScreen()
}