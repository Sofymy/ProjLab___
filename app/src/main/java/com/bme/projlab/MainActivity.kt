package com.bme.projlab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.bme.projlab.ui.screens.MainScreen
import com.bme.projlab.ui.theme.AppppppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppppppTheme {
                MainScreen()
            }
        }
    }
}