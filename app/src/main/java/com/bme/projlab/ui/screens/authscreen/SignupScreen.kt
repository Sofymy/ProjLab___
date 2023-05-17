package com.bme.projlab.ui.screens.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.viewmodel.SignupViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    viewModel: SignupViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordAgain by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize()) {
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Enter your email") },
            placeholder = { Text("Email")}
        )
        Spacer(Modifier.height(5.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Enter your password") }
        )
        Spacer(Modifier.height(5.dp))
        OutlinedTextField(
            value = passwordAgain,
            onValueChange = { passwordAgain = it },
            label = { Text("Enter your password again") }
        )
        Spacer(Modifier.height(5.dp))
        Button(onClick = {
            if (email.isNotBlank()
                && password.isNotBlank()
                && passwordAgain.isNotBlank()) {
                viewModel.signupWithPassword(email, password)
                navigateToLogin()
            }
        }) {
            Text("Sign up")
        }
        Spacer(Modifier.height(20.dp))
        Button(onClick = {
            if (email.isNotBlank() && password.isNotBlank()) {
                viewModel.signupWithFacebook()
            }
        }) {
            Text("Sign up with FB")
        }
        Button(onClick = {
            if (email.isNotBlank() && password.isNotBlank()) {
                viewModel.signupWithGoogle()
            }
        }) {
            Text("Sign up with Google")
        }
    }
}