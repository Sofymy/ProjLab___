package com.bme.projlab.ui.screens.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.viewmodel.LoginViewModel
import com.bme.projlab.ui.theme.GradientBrush

@Composable
fun WelcomeScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
    navigateToSignup: () -> Unit,
    navigateToHome: () -> Unit,
) {
    if(viewModel.currentUser()!=null){
        navigateToHome()
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(GradientBrush)
        ,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Button(onClick = navigateToLogin) {
            Text("Login")
        }
        Button(onClick = navigateToSignup) {
            Text("Sign up")
        }
    }
}