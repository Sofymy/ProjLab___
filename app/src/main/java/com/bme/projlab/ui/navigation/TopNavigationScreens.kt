package com.bme.projlab.ui.navigation

sealed class TopNavigationScreens(var title:String){
    object Main : TopNavigationScreens("Main")
    object FAQ: TopNavigationScreens("FAQ")
    object Settings: TopNavigationScreens("Settings")
    object Logout: TopNavigationScreens("Log out")
}