package com.bme.projlab.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.bme.projlab.ui.theme.Blue
import com.bme.projlab.ui.theme.Grey
import com.bme.projlab.ui.theme.Orange
import com.bme.projlab.ui.theme.White

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
    object Settings : BottomNavItem(
        title = BottomNavigationScreens.Settings.title,
        icon = Icons.Default.Settings
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
        BottomNavItem.Settings,
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

    NavigationBar(
        Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(30.dp))
            .height(60.dp)
        ,
        containerColor = White,
        )
    {
        BottomNavigationItem(
                alwaysShowLabel = false,
                icon = {
                    Icon(
                        imageVector = BottomNavItem.Home.icon,
                        contentDescription = ""
                    )
                },
                label = {
                    Text(text = BottomNavItem.Home.title,
                        style = MaterialTheme.typography.bodyMedium)
                        },
                selected = currentRoute == BottomNavItem.Home.title,
                onClick = {
                    navController.navigate(BottomNavItem.Home.title)
                },
                selectedContentColor = Orange,
                unselectedContentColor = Grey
            )
        BottomNavigationItem(
            alwaysShowLabel = false,
            icon = {
                Icon(
                    imageVector = BottomNavItem.Search.icon,
                    contentDescription = ""
                )
            },
            label = {
                Text(text = BottomNavItem.Search.title,
                    style = MaterialTheme.typography.bodyMedium)
            },
            selected = currentRoute == BottomNavItem.Search.title,
            onClick = {
                navController.navigate(BottomNavItem.Search.title)
            },
            selectedContentColor = Orange,
            unselectedContentColor = Grey
        )
        Spacer(modifier = Modifier.width(50.dp))
        BottomNavigationItem(
            alwaysShowLabel = false,
            icon = {
                Icon(
                    imageVector = BottomNavItem.Settings.icon,
                    contentDescription = ""
                )
            },
            label = {
                Text(text = BottomNavItem.Settings.title,
                    style = MaterialTheme.typography.bodyMedium)
            },
            selected = currentRoute == BottomNavItem.Settings.title,
            onClick = {
                navController.navigate(BottomNavItem.Settings.title)
            },
            selectedContentColor = Orange,
            unselectedContentColor = Grey
        )
        BottomNavigationItem(
            alwaysShowLabel = false,
            icon = {
                Icon(
                    imageVector = BottomNavItem.Profile.icon,
                    contentDescription = ""
                )
            },
            label = {
                Text(text = BottomNavItem.Profile.title,
                    style = MaterialTheme.typography.bodyMedium)
            },
            selected = currentRoute == BottomNavItem.Profile.title,
            onClick = {
                navController.navigate(BottomNavItem.Profile.title)
            },
            selectedContentColor = Orange,
            unselectedContentColor = Grey
        )
    }
}