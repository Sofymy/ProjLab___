package com.bme.projlab.ui.screens

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.viewmodel.HomeViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel())
{
    viewModel.getUsername()?.let {
        Text("Hello, $it!", style = MaterialTheme.typography.bodyLarge)
    }
}