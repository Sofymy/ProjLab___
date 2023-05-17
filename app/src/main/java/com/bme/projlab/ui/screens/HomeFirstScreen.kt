package com.bme.projlab.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.model.response.UsernameResponse
import com.bme.projlab.domain.viewmodel.HomeFirstViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeFirstScreen(
    viewModel: HomeFirstViewModel = hiltViewModel(),
    navigateToHome: () -> Unit
) {
    var username by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )
        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            if (username.isNotBlank()) {
                viewModel.setUsername(username)
            }
        }) {
            androidx.compose.material3.Text("Ok!")
        }
    }
    LaunchedEffect(viewModel.usernameResponse) {
        if (viewModel.usernameResponse == UsernameResponse.Success){
            navigateToHome()
        }
    }
}