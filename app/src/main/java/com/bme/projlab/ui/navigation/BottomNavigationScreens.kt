package com.bme.projlab.ui.navigation

sealed class BottomNavigationScreens(var title:String){
    object Home : BottomNavigationScreens("Home")
    object Trips: BottomNavigationScreens("Trips")
    object Settings: BottomNavigationScreens("Settings")
    object Profile: BottomNavigationScreens("Profile")
}