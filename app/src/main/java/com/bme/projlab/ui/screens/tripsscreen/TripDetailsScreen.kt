package com.bme.projlab.ui.screens.tripdetails

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.viewmodel.HomeViewModel

@Composable
fun TripDetailsScreen(viewModel: HomeViewModel = hiltViewModel()) {
    Text("SearchDatesScreen")
}