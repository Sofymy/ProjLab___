package com.bme.projlab.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.viewmodel.LoginViewModel

@Composable
fun ProfileScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToVisited: () -> Unit,
    navigateToBadges: () -> Unit,
    navigateToReceivedTrips: () -> Unit,
) {
    val space = Modifier.height(20.dp)
    Column {
        VisitedButton(navigateToVisited)
        Spacer(space)
        BadgesButton(navigateToBadges)
        Spacer(space)
        ReceivedTripsButton(navigateToReceivedTrips)
    }
}

@Composable
fun VisitedButton(navigateToVisited: () -> Unit){
    Button(onClick = navigateToVisited) {
        Text("Visited cities")
    }
}

@Composable
fun BadgesButton(navigateToBadges: () -> Unit) {
    Button(onClick = navigateToBadges) {
        Text("Badges")
    }
}

@Composable
fun ReceivedTripsButton(navigateToReceivedTrips: () -> Unit) {
    Button(onClick = navigateToReceivedTrips) {
        Text("Received trips")
    }
}
