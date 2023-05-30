package com.bme.projlab.ui.screens.signup

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
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import com.bme.projlab.domain.viewmodel.SignupViewModel
import com.bme.projlab.ui.theme.GradientBrush
import com.bme.projlab.ui.theme.White
import com.valentinilk.shimmer.shimmer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(
    viewModel: SignupViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordAgain by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text(
            "Register an account", style = MaterialTheme.typography.bodyMedium
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
        Spacer(Modifier.height(20.dp))
        BasicTextField(
            value = password,
            onValueChange = {
                password = it
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
            visualTransformation = PasswordVisualTransformation(),
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
        Spacer(Modifier.height(10.dp))
        BasicTextField(
            value = passwordAgain,
            onValueChange = {
                passwordAgain = it
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
            visualTransformation = PasswordVisualTransformation(),
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
                    if (passwordAgain.isEmpty()) {
                        Text(
                            text = "Password again",
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
                    navigateToLogin()
                }
            }, modifier = Modifier.shimmer()
        ) {
            Text("Sign up!")
        }
    }
}