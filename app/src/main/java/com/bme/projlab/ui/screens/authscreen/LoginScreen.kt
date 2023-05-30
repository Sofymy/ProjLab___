package com.bme.projlab.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.LocalAirport
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.model.response.LoginResponse
import com.bme.projlab.domain.viewmodel.LoginViewModel
import com.bme.projlab.ui.theme.GradientBrush
import com.bme.projlab.ui.theme.White
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToHome: () -> Unit,
    navigateToLoginFirst: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    Column(modifier = Modifier.fillMaxSize().padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "Log in to your account", style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(10.dp))
        BasicTextField(
            value = email,
            onValueChange = {
                email = it
            },
            modifier = Modifier
                .height(57.dp)
                .fillMaxWidth()
                .border(1.dp, brush = GradientBrush, shape = RoundedCornerShape(size = 7.dp))
                .background(
                    color = White,
                )
            ,
            textStyle = TextStyle(
            ),
            singleLine = true,
            cursorBrush = Brush.verticalGradient(colors = listOf(
                Color(0xFF000000),
                Color(0xFF4D4D4D)
            )),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            maxLines = 1
        ) { innerTextField ->
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 17.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Email,
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "Search"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box {
                    if (email.isEmpty()) {
                        Text(
                            text = "Email address",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    Spacer(modifier = Modifier.width(width = 18.dp))
                    innerTextField()
                }
            }
        }
        Spacer(Modifier.height(10.dp))
        BasicTextField(
            value = password,
            onValueChange = {
                password = it
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .height(57.dp)
                .fillMaxWidth()
                .border(1.dp, brush = GradientBrush, shape = RoundedCornerShape(size = 7.dp))
                .background(
                    color = White,
                )
            ,
            textStyle = TextStyle(
            ),
            singleLine = true,
            cursorBrush = Brush.verticalGradient(colors = listOf(
                Color(0xFF000000),
                Color(0xFF4D4D4D)
            )),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            maxLines = 1
        ) { innerTextField ->
            Row(
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 17.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Password,
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "Search"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box {
                    if (password.isEmpty()) {
                        Text(
                            text = "Password",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    Spacer(modifier = Modifier.width(width = 18.dp))
                    innerTextField()
                }
            }
        }
        Spacer(Modifier.height(20.dp))
        Button(
            onClick = {
            if (email.isNotBlank() && password.isNotBlank()) {
                viewModel.loginWithPassword(email, password)
            }
        }, modifier = Modifier.shimmer()
        ) {
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
