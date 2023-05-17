package com.bme.projlab.ui.navigation

sealed class BottomNavigationScreens(var title:String){
    object Home : BottomNavigationScreens("Home")
    object Search: BottomNavigationScreens("Search")
    object Trips: BottomNavigationScreens("Trips")
    object Profile: BottomNavigationScreens("Profile")
}