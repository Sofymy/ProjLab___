package com.bme.projlab.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.bme.projlab.ui.screens.topnavscreen.TopMainScreen
import com.bme.projlab.ui.screens.BadgesScreen
import com.bme.projlab.ui.screens.topnavscreen.FAQScreen
import com.bme.projlab.ui.screens.HomeScreen
import com.bme.projlab.ui.screens.HomeFirstScreen
import com.bme.projlab.ui.screens.LoginScreen
import com.bme.projlab.ui.screens.ProfileScreen
import com.bme.projlab.ui.screens.profilescreen.ReceivedTripsScreen
import com.bme.projlab.ui.screens.searchscreen.SearchDatesExactScreen
import com.bme.projlab.ui.screens.searchscreen.SearchDestinationScreen
import com.bme.projlab.ui.screens.searchscreen.SearchDestinationExactScreen
import com.bme.projlab.ui.screens.searchscreen.SearchDestinationPreferencesScreen
import com.bme.projlab.ui.screens.searchscreen.SearchFromScreen
import com.bme.projlab.ui.screens.searchscreen.SearchResultsScreen
import com.bme.projlab.ui.screens.topnavscreen.SettingsScreen
import com.bme.projlab.ui.screens.topnavscreen.SettingsAccountScreen
import com.bme.projlab.ui.screens.topnavscreen.SettingsNotificationsScreen
import com.bme.projlab.ui.screens.signup.SignupScreen
import com.bme.projlab.ui.screens.tripdetails.TripDetailsScreen
import com.bme.projlab.ui.screens.tripsscreen.TripsScreen
import com.bme.projlab.ui.screens.profilescreen.VisitedScreen
import com.bme.projlab.ui.screens.searchscreen.SearchDestinationPrefResultScreen
import com.bme.projlab.ui.screens.searchscreen.SearchFromAirportScreen
import com.bme.projlab.ui.screens.searchscreen.SearchToAirportScreen
import com.bme.projlab.ui.screens.welcome.WelcomeScreen

sealed class Routes(val route: String){
    object Welcome : Routes("Welcome")
    object Login : Routes("Login")
    object Signup : Routes("Signup")
    object HomeFirst : Routes("HomeFirst")
    object Home : Routes("Home")
    object Profile : Routes("Profile")
    object Search : Routes("Search")
    object SearchFromAirport: Routes("SearchFromAirport")
    object SearchDestination : Routes("SearchDestination")
    object SearchDestinationExact : Routes("SearchDestinationExact")
    object SearchDestinationPreferences : Routes("SearchDestinationPreferences")
    object SearchDestinationPrefResult: Routes("SearchDestinationPrefResult")
    object SearchToAirport: Routes("SearchToAirport")
    object SearchDatesExact : Routes("SearchDatesExact")
    object SearchResults : Routes("SearchResults")
    object TripDetails : Routes("TripDetails")
    object Visited : Routes("Visited")
    object Badges : Routes("Badges")
    object ReceivedTrips : Routes("ReceivedTrips")
    object TopMain: Routes("TopMain")
    object FAQ : Routes("FAQ")
    object Settings : Routes("Settings")
    object SettingsNotifications : Routes("SettingsNotifications")
    object SettingsAccount : Routes("SettingsAccount")
    object Trips : Routes("Trips")
}

const val ENTRANCE_GRAPH_ROUTE = "home"
const val LOGGEDIN_GRAPH_ROUTE = "settings"

@Composable
fun NavGraph(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = ENTRANCE_GRAPH_ROUTE
    ) {
        entranceNavGraph(navController)
        loggedinNavGraph(navController)
    }
}

fun NavGraphBuilder.entranceNavGraph(navController: NavHostController) {
    navigation(
        startDestination = Routes.Welcome.route,
        route = ENTRANCE_GRAPH_ROUTE
    ) {
        composable(Routes.Welcome.route) {
            WelcomeScreen(navigateToSignup = {navController.navigate(Routes.Signup.route)},
                navigateToLogin = {navController.navigate(Routes.Login.route)},
                navigateToHome = {navController.navigate(Routes.Home.route)}
            )
        }
        composable(Routes.Login.route) {
            LoginScreen(navigateToHome = {
                navController.navigate(Routes.Home.route){
                    popUpTo(route = LOGGEDIN_GRAPH_ROUTE) {
                        inclusive = true
                    }
                }
            },
                navigateToLoginFirst = {
                    navController.navigate(Routes.HomeFirst.route){
                        popUpTo(route = LOGGEDIN_GRAPH_ROUTE) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Routes.Signup.route) {
            SignupScreen(navigateToLogin = {navController.navigate(Routes.Login.route)})
        }
    }
}

fun NavGraphBuilder.loggedinNavGraph(
    navController: NavHostController
) {
    navigation(
        startDestination = Routes.Home.route,
        route = LOGGEDIN_GRAPH_ROUTE,
    ) {
        composable(Routes.HomeFirst.route) {
            HomeFirstScreen( navigateToHome = {
                navController.navigate(Routes.Home.route){
                    popUpTo(Routes.Home.route) {
                        inclusive = true
                    }
                }
            }
            )
        }
        composable(Routes.Home.route) {
            HomeScreen()
        }
        composable(Routes.Profile.route) {
            ProfileScreen(
                navigateToBadges = { navController.navigate(Routes.Badges.route) },
                navigateToReceivedTrips = { navController.navigate(Routes.ReceivedTrips.route) },
                navigateToVisited = { navController.navigate(Routes.Visited.route) }
            )
        }
        composable(Routes.Trips.route) {
            TripsScreen()
        }
        composable(Routes.Search.route) {
            val fromAirport = "CDG"
            val toAirport = "BUD"
            val toDestination = "Budapest"
            SearchFromScreen(onItemClick = { fromDestination ->
                navController.navigate("${Routes.SearchDatesExact.route}/$fromAirport/$fromDestination/$toAirport/$toDestination")
                {
                    launchSingleTop = true
                }
            })
        }
        composable("${Routes.SearchFromAirport.route}/{fromDestination}") {
             navBackStackEntry ->
                val fromDestination = navBackStackEntry.arguments?.getString("fromDestination")
            fromDestination?.let {
                SearchFromAirportScreen(
                    fromDestination = fromDestination,
                    onItemClick = { fromAirport ->
                        navController.navigate("${Routes.SearchDestination.route}/$fromAirport/$fromDestination")
                        {
                            launchSingleTop = true
                        }
                })
            }
        }
        composable("${Routes.SearchDestination.route}/{fromAirport}/{fromDestination}")
            { navBackStackEntry ->
                val fromAirport = navBackStackEntry.arguments?.getString("fromAirport")
                val fromDestination = navBackStackEntry.arguments?.getString("fromDestination")
                fromAirport?.let {
                    SearchDestinationScreen(
                        fromAirport = fromAirport,
                        navigateToPreferences = { navController.navigate("${Routes.SearchDestinationPreferences.route}/$fromAirport/$fromDestination") },
                        navigateToExact =  { navController.navigate("${Routes.SearchDestinationExact.route}/$fromAirport/$fromDestination") }
                    )
                }
            }
        composable("${Routes.SearchDestinationExact.route}/{fromAirport}/{fromDestination}")
        { navBackStackEntry ->
            val fromAirport = navBackStackEntry.arguments?.getString("fromAirport")
            val fromDestination = navBackStackEntry.arguments?.getString("fromDestination")
            fromAirport?.let {
                SearchDestinationExactScreen(fromAirport = fromAirport,
                    onItemClick = { toDestination ->
                        navController.navigate("${Routes.SearchToAirport.route}/$fromAirport/$fromDestination/$toDestination")
                    }
                )
            }
        }
        composable("${Routes.SearchDestinationPreferences.route}/{fromDestination}/{fromAirport}")
        { navBackStackEntry ->
            val fromAirport = navBackStackEntry.arguments?.getString("fromAirport")
            val fromDestination = navBackStackEntry.arguments?.getString("fromDestination")
            fromAirport?.let {
                SearchDestinationPreferencesScreen(fromAirport = fromAirport,
                    navigateToDestinationPrefResults = { b: Boolean, b1: Boolean, b2: Boolean, b3: Boolean ->
                        navController.navigate("${Routes.SearchDestinationPrefResult.route}/$fromAirport/$fromDestination/$b/$b1/$b2/$b3")
                    }
                )
            }
        }

        composable("${Routes.SearchDestinationPrefResult.route}/{fromAirport}/{fromDestination}/{b}/{b1}/{b2}/{b3}")
        { navBackStackEntry ->
            val fromAirport = navBackStackEntry.arguments?.getString("fromAirport")
            val fromDestination = navBackStackEntry.arguments?.getString("fromDestination")
            val capital = navBackStackEntry.arguments?.getString("b").toBoolean()
            val historical = navBackStackEntry.arguments?.getString("b1").toBoolean()
            val warm = navBackStackEntry.arguments?.getString("b2").toBoolean()
            val first = navBackStackEntry.arguments?.getString("b3").toBoolean()
            fromAirport?.let {
                SearchDestinationPrefResultScreen(fromAirport = fromAirport,
                    capital = capital,
                    historical = historical,
                    warm = warm,
                    first = first,
                    navigateToAirport = { toDestination ->
                        navController.navigate("${Routes.SearchToAirport.route}/$fromAirport/$fromDestination/$toDestination")
                    }
                )
            }
        }

        composable("${Routes.SearchToAirport.route}/{fromAirport}/{fromDestination}/{toDestination}")
        { navBackStackEntry ->
            val fromAirport = navBackStackEntry.arguments?.getString("fromAirport")
            val fromDestination = navBackStackEntry.arguments?.getString("fromDestination")
            val toDestination = navBackStackEntry.arguments?.getString("toDestination")
            fromAirport?.let {
                if (toDestination != null) {
                    SearchToAirportScreen(fromAirport = fromAirport, toDestination = toDestination,
                         onItemClick = {toAirport ->
                            navController.navigate("${Routes.SearchDatesExact.route}/$fromAirport/$fromDestination/$toAirport/$toDestination")
                        })
                }
            }
        }

        composable("${Routes.SearchDatesExact.route}/{fromAirport}/{fromDestination}/{toAirport}/{toDestination}")
        { navBackStackEntry ->
            val fromAirport = navBackStackEntry.arguments?.getString("fromAirport")
            val toAirport = navBackStackEntry.arguments?.getString("toAirport")
            val fromDestination = navBackStackEntry.arguments?.getString("fromDestination")
            val toDestination = navBackStackEntry.arguments?.getString("toDestination")
            fromAirport?.let {
                SearchDatesExactScreen (
                    fromAirport = fromAirport,
                    toAirport = toAirport,
                    fromDestination = fromDestination,
                    toDestination = toDestination,
                    navigateToResults = { fromDate, toDate ->
                        navController.navigate("${Routes.SearchResults.route}/$fromAirport/$toAirport/$fromDate/$toDate")
                    }
                )
            }
        }

        composable("${Routes.SearchResults.route}/{fromAirport}/{toAirport}/{fromDate}/{toDate}")
        { navBackStackEntry ->
            val fromAirport = navBackStackEntry.arguments?.getString("fromAirport")
            val toAirport = navBackStackEntry.arguments?.getString("toAirport")
            val fromDate = navBackStackEntry.arguments?.getString("fromDate")
            val toDate = navBackStackEntry.arguments?.getString("toDate")
            if (fromAirport != null) {
                if (toAirport != null) {
                    if (fromDate != null) {
                        if (toDate != null) {
                            SearchResultsScreen(
                                fromAirport = fromAirport,
                                toAirport = toAirport,
                                fromDate = fromDate,
                                toDate = toDate
                            )
                        }
                    }
                }
            }
        }
        composable(Routes.TripDetails.route){
            TripDetailsScreen()
        }
        composable(Routes.Visited.route){
            VisitedScreen()
        }
        composable(Routes.Badges.route){
            BadgesScreen()
        }
        composable(Routes.ReceivedTrips.route){
            ReceivedTripsScreen()
        }
        composable(Routes.TopMain.route){
            TopMainScreen(
                navigateToFAQ = { navController.navigate(Routes.FAQ.route) },
                navigateToSettings = { navController.navigate(Routes.Settings.route) },
                navigateToWelcome = {
                    navController.navigate(Routes.Welcome.route){
                        popUpTo(route = ENTRANCE_GRAPH_ROUTE) {
                            inclusive = true
                        }
                    }
                }
            )
        }
        composable(Routes.FAQ.route){
            FAQScreen()
        }
        composable(Routes.Settings.route){
            SettingsScreen()
        }
        composable(Routes.SettingsNotifications.route){
            SettingsNotificationsScreen()
        }
        composable(Routes.SettingsAccount.route){
            SettingsAccountScreen()
        }

    }
}