package com.bme.projlab.ui.screens.searchscreen

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.viewmodel.SearchDestinationPreferencesViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun SearchDestinationPreferencesScreen(
    viewModel: SearchDestinationPreferencesViewModel = hiltViewModel(),
    fromAirport: String,
    navigateToDestinationPrefResults: (Boolean, Boolean, Boolean, Boolean) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    val categories = arrayOf("$", "$$", "$$$")
    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(categories[0]) }
    var first by remember { mutableStateOf(true) }
    var warm by remember { mutableStateOf(true) }

    val stateCapital = remember { mutableStateOf(false) }
    val stateHistorical = remember { mutableStateOf(false) }
    val stateUnbeaten = remember { mutableStateOf(false) }

    val stateRoad = remember { mutableStateOf(false) }
    val stateRail = remember { mutableStateOf(false) }

    Column(
        Modifier
            .padding(10.dp)
    ) {
        Phases(0.6f)

        //First
        Row {
            Text("First")
            Switch(
                modifier = Modifier.semantics { contentDescription = "Demo" },
                checked = first,
                onCheckedChange = { first = it })
        }


        //Climate
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Climate")
            OutlinedButton(onClick = {
                warm = !warm
            }) {
                if (warm) {
                    Text("Hot")
                } else Text("Cold")
            }
        }

        //Traits
        Text("Traits")
        Row (
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            FilterChip(
                selected = stateCapital.value,
                label = {Text("Capital")},
                onClick = { stateCapital.value = !stateCapital.value },
                modifier = Modifier.alpha(if (stateCapital.value) 1.0f else 0.5f),
                leadingIcon = if (stateCapital.value) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Localized Description",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                }
            )

            Spacer(modifier = Modifier.width(10.dp))

            FilterChip(
                selected = stateHistorical.value,
                label = {Text("Historical")},
                onClick = { stateHistorical.value = !stateHistorical.value },
                modifier = Modifier.alpha(if (stateHistorical.value) 1.0f else 0.5f),
                leadingIcon = if (stateHistorical.value) {
                    {
                        Icon(
                            imageVector = Icons.Filled.Done,
                            contentDescription = "Localized Description",
                            modifier = Modifier.size(FilterChipDefaults.IconSize)
                        )
                    }
                } else {
                    null
                }
            )

        }

        //Button
        Button(onClick = {
            navigateToDestinationPrefResults(stateCapital.value, stateHistorical.value, warm, first)
        }) {
            Text("Next")
        }
        }
    }

