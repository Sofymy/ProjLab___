package com.bme.projlab.ui.screens.topnavscreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.viewmodel.HomeViewModel

@Composable
fun FAQScreen(viewModel: HomeViewModel = hiltViewModel()) {
    Text("FAQ")
}