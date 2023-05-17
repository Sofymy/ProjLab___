package com.bme.projlab.ui.screens.searchscreen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.bme.projlab.ui.items.SearchDatesTabItems

@Composable
fun SearchDatesScreen(
    navigateToExact: () -> Unit,
    navigateToPreferences: () -> Unit,
    fromAirport: String,
    toAirport: String?
) {
    Column{
        Text("Search dates by...")
        Text("Select")
        RadioButtons(navigateToExact, navigateToPreferences)
    }
}

@Composable
fun RadioButtons(navigateToExact: () -> Unit, navigateToPreferences: () -> Unit) {
    val radioOptions = listOf(
        SearchDatesTabItems.SearchDatesExact,
        SearchDatesTabItems.SearchDatesPreferences,
        SearchDatesTabItems.SearchDatesRandom
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
    items: List<SearchDatesTabItems>,
    selected: SearchDatesTabItems,
    setSelected: (selected: SearchDatesTabItems) -> Unit,
    navigateToExact: () -> Unit,
    navigateToPreferences: () -> Unit,
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
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
                            selectedColor = Color.Magenta
                        )
                    )
                    Text(text = item.title, modifier = Modifier.padding(start = 8.dp))
                }
            }
        }
        Button(onClick = {
            when(selected.title) {
                "Exact" -> {navigateToExact()}
                "Preferences" -> {navigateToPreferences()}
                "Random" -> {}
            }
        }){
            Text("Next")
        }
    }
}
