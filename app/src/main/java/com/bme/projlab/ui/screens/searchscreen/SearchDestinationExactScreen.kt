package com.bme.projlab.ui.screens.searchscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.viewmodel.SearchDestinationViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchDestinationExactScreen(
    viewModel: SearchDestinationViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit,
    fromAirport: String
) {
    Column{
        Scaffold(topBar = {
            SearchViewDestination(viewModel, "Where would you like to go?")
        }) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                SearchListDestination(viewModel, onItemClick)
            }
        }
    }
}