package com.bme.projlab.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Colors
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
import com.bme.projlab.ui.theme.Purple
import com.bme.projlab.ui.theme.GradientBrush
import com.bme.projlab.ui.theme.TransparentBrush
import com.bme.projlab.ui.theme.White

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector
) {
    object Home : BottomNavItem(
        title = BottomNavigationScreens.Home.title,
        icon = Icons.Default.Home,
    )
    object Trips : BottomNavItem(
        title = BottomNavigationScreens.Trips.title,
        icon = Icons.Default.AirplaneTicket
    )
    object SettingsAccount : BottomNavItem(
        title = BottomNavigationScreens.SettingsAccount.title,
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
            .background(TransparentBrush)
            .padding(20.dp)
            .clip(RoundedCornerShape(30.dp))
            .height(60.dp)
            //.border(width = 0.dp, brush = GradientBrush, shape = RoundedCornerShape(30.dp))
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
                        },
                selected = currentRoute == BottomNavItem.Home.title,
                onClick = {
                    navController.navigate(BottomNavItem.Home.title)
                },
                selectedContentColor = Purple,
                unselectedContentColor = Grey
            )
        BottomNavigationItem(
            alwaysShowLabel = false,
            icon = {
                Icon(
                    imageVector = BottomNavItem.Trips.icon,
                    contentDescription = ""
                )
            },
            label = {
            },
            selected = currentRoute == BottomNavItem.Trips.title,
            onClick = {
                navController.navigate(BottomNavItem.Trips.title)
            },
            selectedContentColor = Purple,
            unselectedContentColor = Grey
        )
        Spacer(modifier = Modifier.width(70.dp))
        BottomNavigationItem(
            alwaysShowLabel = false,
            icon = {
                Icon(
                    imageVector = BottomNavItem.Profile.icon,
                    contentDescription = ""
                )
            },
            label = {
            },
            selected = currentRoute == BottomNavItem.Profile.title,
            onClick = {
                navController.navigate(BottomNavItem.Profile.title)
            },
            selectedContentColor = Purple,
            unselectedContentColor = Grey
        )
        BottomNavigationItem(
            alwaysShowLabel = false,
            icon = {
                Icon(
                    imageVector = BottomNavItem.SettingsAccount.icon,
                    contentDescription = ""
                )
            },
            label = {
            },
            selected = currentRoute == BottomNavItem.SettingsAccount.title,
            onClick = {
                navController.navigate(BottomNavItem.SettingsAccount.title)
            },
            selectedContentColor = Purple,
            unselectedContentColor = Grey
        )
    }
}