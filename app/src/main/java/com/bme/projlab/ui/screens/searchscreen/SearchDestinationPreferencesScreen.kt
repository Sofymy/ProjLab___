package com.bme.projlab.ui.screens.searchscreen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.viewmodel.SearchDestinationPreferencesViewModel
import com.bme.projlab.ui.theme.Blue
import com.bme.projlab.ui.theme.GradientBrush
import com.bme.projlab.ui.theme.Grey
import com.bme.projlab.ui.theme.Purple
import com.bme.projlab.ui.theme.White
import com.valentinilk.shimmer.shimmer

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun SearchDestinationPreferencesScreen(
    viewModel: SearchDestinationPreferencesViewModel = hiltViewModel(),
    fromAirport: String,
    navigateToDestinationPrefResults: (Boolean, Boolean, Boolean, Boolean) -> Unit,
) {
    var first by remember { mutableStateOf(true) }
    var warm by remember { mutableStateOf(true) }

    val stateCapital = remember { mutableStateOf(false) }
    val stateHistorical = remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(15.dp)
    ) {
        Phases(0.6f)
        Spacer(modifier = Modifier.height(10.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "How would you describe your dream destination?",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(20.dp))

        //First
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(White)
                .border(
                    border = BorderStroke(1.dp, GradientBrush),
                    shape = RoundedCornerShape(7.dp)
                )
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text("Explore new places",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(10.dp))
            Switch(
                modifier = Modifier.semantics { contentDescription = "Demo" },
                checked = first,
                colors = androidx.compose.material3.SwitchDefaults.colors(
                    checkedThumbColor = Grey,
                    uncheckedThumbColor = Grey,
                    checkedTrackColor = Purple,
                    uncheckedTrackColor = White
                ),
                onCheckedChange = { first = it })
        }
        Spacer(modifier = Modifier.height(10.dp))
        Icon(imageVector = Icons.Default.ArrowDropDown, tint = Purple, contentDescription = "",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))
        //Climate
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(White)
                .border(
                    border = BorderStroke(1.dp, GradientBrush),
                    shape = RoundedCornerShape(7.dp)
                )
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Text("Climate",
                style = MaterialTheme.typography.bodyMedium
            )
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedButton(onClick = {
                warm = !warm
            }) {
                if (warm) {
                    Text("Hot", style = MaterialTheme.typography.bodyMedium, color = com.bme.projlab.ui.theme.Text)
                } else Text("Cold", style = MaterialTheme.typography.bodyMedium,  color = com.bme.projlab.ui.theme.Text)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Icon(imageVector = Icons.Default.ArrowDropDown, tint = Purple, contentDescription = "",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.height(10.dp))

        //Traits
        Column(modifier = Modifier
            .background(White)
            .border(
                border = BorderStroke(1.dp, GradientBrush),
                shape = RoundedCornerShape(7.dp)
            )
            .fillMaxWidth()
            .padding(15.dp)) {
            Text(
                "What kind of city would you like to travel?",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                "Choose zero or more traits to find your dream destination",
                style = MaterialTheme.typography.bodySmall
            )
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                FilterChip(
                    selected = stateCapital.value,
                    shape = RoundedCornerShape(20.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = White,
                        labelColor = com.bme.projlab.ui.theme.Text,
                        selectedContainerColor = Purple,
                        selectedLabelColor = White
                    ),
                    label = {
                        Text(
                            "Capital",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(10.dp)
                        )
                    },
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
                    shape = RoundedCornerShape(20.dp),
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = White,
                        labelColor = com.bme.projlab.ui.theme.Text,
                        selectedContainerColor = Purple,
                        selectedLabelColor = White
                    ),
                    label = {
                        Text(
                            "Historical",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(10.dp)
                        )
                    },
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
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = {
            navigateToDestinationPrefResults(stateCapital.value, stateHistorical.value, warm, first)
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue
            ),
            modifier = Modifier.shimmer()
        ){
            Text(
                "Next",
                style = MaterialTheme.typography.bodyMedium,
                color = White
            )
        }
        }
    }

