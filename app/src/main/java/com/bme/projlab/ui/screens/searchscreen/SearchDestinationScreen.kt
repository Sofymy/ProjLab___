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


@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun SearchViewDestination(
    searchDestinationViewModel: SearchDestinationViewModel,
    text: String
) {
    var query by remember { mutableStateOf("") }
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 10.dp)
    ) {
        BasicTextField(
            value = query,
            onValueChange = {
                query = it
                coroutineScope.launch {
                    searchDestinationViewModel.performQuery(it)
                }
            },
            modifier = Modifier.height(38.dp).fillMaxWidth()
                .background(color = Color(0xFFD2F3F2), shape = RoundedCornerShape(size = 16.dp)),
            textStyle = TextStyle(
            ),
            singleLine = true,
            cursorBrush = Brush.verticalGradient(colors = listOf(Color(0xFF000000),
                Color(0xFF4D4D4D))),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            maxLines = 1,
            decorationBox = {innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (query.isEmpty()) {
                        Text(
                            text = text,
                        )
                        coroutineScope.launch {
                            searchDestinationViewModel.loadDestinations()
                        }
                    }
                    else
                        coroutineScope.launch {
                            searchDestinationViewModel.performQuery(query)                        }

                    innerTextField()

                }
            })
    }
}

@Composable
fun SearchListItemDestination(
    destinationItem: Destination,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit
) {
    Surface(
        modifier
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Row(modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
            Column(modifier = Modifier
                .clickable(
                    onClick = {
                        destinationItem.name?.let { onItemClick(it) }
                    })
                .height(57.dp)
                .fillMaxWidth()){
                destinationItem.name?.let {
                    Text(
                        text = it,
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp, vertical = 12.dp)
                            .wrapContentWidth(Alignment.Start)
                    )
                }
            }
        }
    }
}

@Composable
fun SearchListDestination(
    searchDestinationViewModel: SearchDestinationViewModel,
    onItemClick: (String) -> Unit
){
    val list = searchDestinationViewModel.list.observeAsState().value
    LazyColumn {
        if (!list.isNullOrEmpty()) {
            items(list) { destinationItem ->
                SearchListItem(destinationItem
                ) { fromDestination ->
                    onItemClick(fromDestination)
                }
                Surface(Modifier.padding(horizontal = 24.dp)) {
                    Divider(
                    )
                }
            }
        }
    }
}