package com.bme.projlab.ui.screens.topnavscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.viewmodel.HomeViewModel
import com.bme.projlab.domain.viewmodel.TopViewModel

@Composable
fun TopMainScreen(
    viewModel: TopViewModel = hiltViewModel(),
    navigateToSettings: () -> Unit,
    navigateToFAQ: () -> Unit,
    navigateToWelcome: () -> Unit
) {
    Column {
        Button(onClick = { navigateToFAQ() }) {
            Text(text = "FAQ")
        }
        Button(onClick = { navigateToSettings() }) {
            Text(text = "Settings")
        }
        Button(onClick = {
            viewModel.signOut()
            navigateToWelcome() }) {
            Text(text = "Log out")
        }
    }
}