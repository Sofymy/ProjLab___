package com.bme.projlab.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.model.response.LoginResponse
import com.bme.projlab.domain.viewmodel.LoginViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToLoginFirst: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") }
        )
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )
        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            if (email.isNotBlank() && password.isNotBlank()) {
                viewModel.loginWithPassword(email, password)
            }
        }) {
            Text("Login")
        }
    }

    LaunchedEffect(viewModel.loginResponse) {
        if (viewModel.loginResponse == LoginResponse.Success){
            navigateToHome()
        }
        else if (viewModel.loginResponse == LoginResponse.SuccessFirst){
            navigateToLoginFirst()
        }
    }
}
