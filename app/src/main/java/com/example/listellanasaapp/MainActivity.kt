package com.example.listellanasaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.listellademoapp.presentation.ui.view.NavigationItem
import com.example.listellanasaapp.ui.apod.APODScreen
import com.example.listellanasaapp.ui.marsrover.MarsRoverScreen
import com.example.listellanasaapp.ui.menu.MenuScreen
import com.example.listellanasaapp.ui.recentphotos.RecentPhotosScreen
import com.example.listellanasaapp.ui.theme.ListellaNASAAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListellaNASAAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()


    Scaffold(
        topBar = { TopBar() },
        bottomBar = {
            BottomNavigationBar(navController)
        },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                Navigation(navControlller = navController)
            }
        },
        backgroundColor = Color.LightGray
    )
}

@Composable
fun TopBar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name), fontSize = 12.sp) },
        backgroundColor = Color.White,
        contentColor = Color.Black,
        modifier = Modifier.fillMaxHeight(0.04F)
    )
}

@Composable
fun BottomNavigationBar(navControlller: NavController) {
    val items = listOf(
        NavigationItem.APOD,
        NavigationItem.MarsRover,
        NavigationItem.RecentPhotos,
        NavigationItem.Menu
    )
    BottomNavigation(
        backgroundColor = Color.Black,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navControlller.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = { Text(text = item.title) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.White.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.route,
                onClick = {
                    navControlller.navigate(item.route) {
                        navControlller.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                })
        }
    }
}

@Composable
fun Navigation(navControlller: NavHostController) {
    NavHost(navController = navControlller, startDestination = NavigationItem.APOD.route) {
        composable(NavigationItem.APOD.route) {
            APODScreen()
        }
        composable(NavigationItem.MarsRover.route) {
            MarsRoverScreen()
        }
        composable(NavigationItem.RecentPhotos.route) {
            RecentPhotosScreen()

        }
        composable(NavigationItem.Menu.route) {
            MenuScreen()
        }
    }

}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ListellaNASAAppTheme {
        APODScreen()
    }
}