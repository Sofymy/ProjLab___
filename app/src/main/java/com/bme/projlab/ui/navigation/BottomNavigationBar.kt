package com.bme.projlab.ui.navigation

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AirplaneTicket
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        title = BottomNavigationScreens.Home.title,
        icon = Icons.Default.Home,
    )
    object Search : BottomNavItem(
        title = BottomNavigationScreens.Search.title,
        icon = Icons.Default.Search
    )
    object Trips : BottomNavItem(
        title = BottomNavigationScreens.Trips.title,
        icon = Icons.Default.AirplaneTicket
    )
    object Profile : BottomNavItem(
        title = BottomNavigationScreens.Profile.title,
        icon = Icons.Default.Face
    )
}

@Composable
fun BottomNavigationBar(
    navController: NavController
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Search,
        BottomNavItem.Trips,
        BottomNavItem.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (currentRoute == null
        || currentRoute == Routes.Welcome.route
        || currentRoute == Routes.Login.route
        || currentRoute == Routes.Signup.route) {
        return
    }

    BottomNavigation(Modifier.padding(25.dp).clip(RoundedCornerShape(30.dp, 30.dp, 30.dp, 30.dp)).height(60.dp)
        ) {
        items.forEach { item ->
            BottomNavigationItem(
                alwaysShowLabel = false,
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = ""
                    )
                },
                label = { Text(text = item.title) },
                selected = currentRoute == item.title,
                onClick = {
                    navController.navigate(item.title)
                }
            )
        }
    }
}