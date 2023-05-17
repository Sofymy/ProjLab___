package com.bme.projlab.ui.screens.searchscreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.viewmodel.HomeViewModel

@Composable
fun SearchDatesPreferencesScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navigateToResults: () -> Unit,
    fromAirport: String,
    toAirport: String?
) {

    var checked by remember { mutableStateOf(false) }
    var tripLength by remember { mutableStateOf(0f) }

    Column(Modifier.padding(10.dp)) {
        Row() {
            Text("Work-friendly trip")
            Switch(
                modifier = Modifier.semantics { contentDescription = "Demo" },
                checked = checked,
                onCheckedChange = { checked = it })
        }
        Text("Trip length")
        Text(text = tripLength.toString())
        Slider(
            value = tripLength,
            onValueChange = { tripLength = it },
            valueRange = 0f..100f,
            onValueChangeFinished = {},
            steps = 4
        )

        Button(onClick = {navigateToResults()}){
            Text("Next")
        }
    }
}