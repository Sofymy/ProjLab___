package com.bme.projlab.ui.screens.searchscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.model.element.Destination
import com.bme.projlab.domain.viewmodel.SearchDestinationViewModel
import com.bme.projlab.ui.items.SearchDestinationTabItems
import kotlinx.coroutines.launch

@Composable
fun SearchDestinationScreen(
    viewModel: SearchDestinationViewModel = hiltViewModel(),
    navigateToExact: () -> Unit,
    navigateToPreferences: () -> Unit,
    fromAirport: String,
) {
    Column{
        Phases(0.6f)
        Text("Search destination by...")
        Text("Select")
        RadioButtonsDestination(navigateToExact, navigateToPreferences)
    }
}

@Composable
fun RadioButtonsDestination(navigateToExact: () -> Unit, navigateToPreferences: () -> Unit) {
    val radioOptions = listOf(
        SearchDestinationTabItems.SearchDestinationExact,
        SearchDestinationTabItems.SearchDestinationPreferences,
        SearchDestinationTabItems.SearchDestinationRandom
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
    CompositionLocalProvider(LocalLayoutDirection provides androidx.compose.ui.unit.LayoutDirection.Rtl) {
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
