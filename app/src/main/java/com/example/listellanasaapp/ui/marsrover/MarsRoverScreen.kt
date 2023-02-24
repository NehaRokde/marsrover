package com.example.listellanasaapp.ui.marsrover

import android.annotation.SuppressLint
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.listellanasaapp.MainScreen
import com.example.listellanasaapp.R
import com.example.listellanasaapp.TopBar
import com.example.listellanasaapp.ui.marsrover.curosity.CuriosityScreen
import com.example.listellanasaapp.ui.marsrover.spirit.SpiritScreen
import com.google.accompanist.pager.*
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.launch

@Composable
fun MarsRoverScreen() {
    TabLayoutScreen()
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun TabLayoutScreen() {

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
    Column {
        TabRow(selectedTabIndex = tabIndex) {
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
    TopBar()
}