package com.bme.projlab.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.IconButton
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch


sealed class TopNavItem(
    val title: String,
    val icon: ImageVector
) {
    object Main : TopNavItem(
        title = TopNavigationScreens.Main.title,
        icon = Icons.Default.Home,
    )
    object FAQ : TopNavItem(
        title = TopNavigationScreens.FAQ.title,
        icon = Icons.Default.Search
    )
    object Settings : BottomNavItem(
        title = TopNavigationScreens.Settings.title,
        icon = Icons.Default.AirplaneTicket
    )
    object Logout : BottomNavItem(
        title = TopNavigationScreens.Logout.title,
        icon = Icons.Default.Face
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun TopNavigationBar(
    navController: NavController
){
    val icons = listOf(Icons.Default.QuestionAnswer, Icons.Default.Face, Icons.Default.Logout)
    val items = listOf(Routes.FAQ.route, Routes.Settings.route, "Log out")
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (currentRoute == null
        || currentRoute == Routes.Welcome.route
        || currentRoute == Routes.Login.route
        || currentRoute == Routes.Signup.route) {
        return
    }

    CenterAlignedTopAppBar(
        title = {
            Text(
                "Projlab",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color.White
            )
        },
        actions = {
            IconButton(onClick = {
                navController.navigate(Routes.TopMain.route)
            }) {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    contentDescription = "Localized description",
                )
            }
        }
    )
}