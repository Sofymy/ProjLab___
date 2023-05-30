package com.bme.projlab.ui.screens.searchscreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.viewmodel.SearchDestinationViewModel
import com.bme.projlab.ui.items.SearchDestinationTabItems
import com.bme.projlab.ui.theme.Blue
import com.bme.projlab.ui.theme.Purple
import com.bme.projlab.ui.theme.White
import com.valentinilk.shimmer.shimmer

@Composable
fun SearchDestinationScreen(
    viewModel: SearchDestinationViewModel = hiltViewModel(),
    navigateToExact: () -> Unit,
    navigateToPreferences: () -> Unit,
    fromAirport: String,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(15.dp)
    ){
        Phases(0.5f)
        Column() {
            Text("Do you know already where would like to go?",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(20.dp))
            RadioButtonsDestination(navigateToExact, navigateToPreferences)
        }

    }
}

@Composable
fun RadioButtonsDestination(navigateToExact: () -> Unit, navigateToPreferences: () -> Unit) {
    val radioOptions = listOf(
        SearchDestinationTabItems.SearchDestinationExact,
        SearchDestinationTabItems.SearchDestinationPreferences,
    )
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[1] ) }
    Column {
        RadioGroup(
            items = radioOptions, selected = selectedOption, onOptionSelected, navigateToExact, navigateToPreferences
        )
    }

}


@Composable
fun RadioGroup(
    items: List<SearchDestinationTabItems>,
    selected: SearchDestinationTabItems,
    setSelected: (selected: SearchDestinationTabItems) -> Unit,
    navigateToExact: () -> Unit,
    navigateToPreferences: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
        ,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        items.forEach { item ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = selected == item,
                    onClick = {
                        setSelected(item)
                    },
                    enabled = true,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = Purple
                    )
                )
                Text(text = item.title,
                    modifier = Modifier.padding(start = 8.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                when (selected.title) {
                    "Yes, of course!" -> {
                        navigateToExact()
                    }

                    "No, surprise me!" -> {
                        navigateToPreferences()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue
            ),
            modifier = Modifier.shimmer()
        ) {
            Text(
                "Next",
                style = MaterialTheme.typography.bodyMedium,
                color = White
            )
        }
    }
}

