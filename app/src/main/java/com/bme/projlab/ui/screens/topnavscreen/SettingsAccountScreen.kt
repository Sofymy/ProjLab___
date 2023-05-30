package com.bme.projlab.ui.screens.topnavscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocalAirport
import androidx.compose.material.icons.filled.Password
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.ui.layout.Column
import com.bme.projlab.data.repository.ModificationRepositoryImpl
import com.bme.projlab.data.repository.UserRepositoryImpl
import com.bme.projlab.domain.viewmodel.HomeViewModel
import com.bme.projlab.domain.viewmodel.SettingsAccountViewModel
import com.bme.projlab.ui.theme.Blue
import com.bme.projlab.ui.theme.GradientBrush
import com.bme.projlab.ui.theme.Grey
import com.bme.projlab.ui.theme.White
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsAccountScreen(
    viewModel: SettingsAccountViewModel = hiltViewModel()
) {

    var password by remember { mutableStateOf("") }
    var passwordAgain by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()

    androidx.compose.foundation.layout.Column(
        modifier = Modifier.padding(15.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        Text("Modify password",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text("\"Choosing a hard-to-guess, but easy-to-remember password is important!\"\n" +
                "Kevin Mitnick",
            style = MaterialTheme.typography.bodySmall,
            color = Grey
        )
        androidx.compose.foundation.layout.Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.height(20.dp))
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
                                text = "Enter your new password",
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
                visualTransformation = PasswordVisualTransformation(),
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
                        if (passwordAgain.isEmpty()) {
                            Text(
                                text = "Enter again your new password",
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

            Button(onClick = {
                if (password.isNotBlank() && password == passwordAgain) {
                    coroutineScope.launch {
                        viewModel.modifyPassword(password)
                        password = ""
                        passwordAgain = ""
                    }
                }}
                ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = Blue
                ),
                modifier = Modifier
                    .shimmer()
                    .align(Alignment.CenterHorizontally)
                ) {
                Text(
                    "OK",
                    style = MaterialTheme.typography.bodyMedium,
                    color = White
                )
            }
        }
    }
}