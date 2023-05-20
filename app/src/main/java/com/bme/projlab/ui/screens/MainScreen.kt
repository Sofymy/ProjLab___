package com.bme.projlab.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AirplaneTicket
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.bme.projlab.ui.navigation.*
import com.bme.projlab.ui.theme.Background
import com.bme.projlab.ui.theme.Grey
import com.bme.projlab.ui.theme.White

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(){
    val navController = rememberNavController()
    Scaffold(
        backgroundColor = Background,
        floatingActionButton = {
            FloatingActionButton(
                modifier = Modifier
                    .padding(0.dp, 89.dp, 0.dp, 0.dp)
                    .width(75.dp)
                    .height(75.dp)

                ,
                shape = CircleShape,
                onClick = {
                    navController.navigate(Routes.Trips.route)
                },
                containerColor = MaterialTheme.colorScheme.secondary,
                elevation = FloatingActionButtonDefaults.elevation(
                    defaultElevation = 0.dp
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.AirplaneTicket,
                    tint = White,
                    contentDescription = "icon")
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        bottomBar = { BottomNavigationBar(navController = navController) }
    ) {
        NavGraph(
            modifier = Modifier
                .fillMaxSize(),
                navController = navController
        )
    }
}