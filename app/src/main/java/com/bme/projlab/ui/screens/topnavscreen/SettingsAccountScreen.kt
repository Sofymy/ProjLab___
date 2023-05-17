package com.bme.projlab.ui.screens.topnavscreen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.ui.layout.Column
import com.bme.projlab.data.repository.ModificationRepositoryImpl
import com.bme.projlab.data.repository.UserRepositoryImpl
import com.bme.projlab.domain.viewmodel.HomeViewModel
import com.bme.projlab.domain.viewmodel.SettingsAccountViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsAccountScreen(
    viewModel: SettingsAccountViewModel = hiltViewModel()
) {

    var password by remember { mutableStateOf("") }
    var passwordAgain by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    androidx.compose.foundation.layout.Column() {
        Text("Modify password")
        androidx.compose.foundation.layout.Column(modifier = Modifier.fillMaxSize()) {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") }
            )
            Spacer(Modifier.height(20.dp))
            OutlinedTextField(
                value = passwordAgain,
                onValueChange = { passwordAgain = it },
                label = { Text("Password") }
            )
            Spacer(Modifier.height(20.dp))
            Button(onClick = {
                if (password.isNotBlank() && password == passwordAgain) {
                    coroutineScope.launch {
                        viewModel.modifyPassword(password)
                    }
                }
            }) {
                Text("Login")
            }
        }
    }
}