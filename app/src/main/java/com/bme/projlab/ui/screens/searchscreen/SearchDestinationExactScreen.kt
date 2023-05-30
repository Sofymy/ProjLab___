package com.bme.projlab.ui.screens.searchscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationCity
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.model.element.Destination
import com.bme.projlab.domain.model.element.DestinationTraits
import com.bme.projlab.domain.viewmodel.SearchDestinationViewModel
import com.bme.projlab.ui.theme.GradientBrush
import com.bme.projlab.ui.theme.Grey
import com.bme.projlab.ui.theme.White
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchDestinationExactScreen(
    viewModel: SearchDestinationViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit,
    fromAirport: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(15.dp)
    ){
        Phases(0.6f)
        Scaffold(topBar = {
            SearchViewDestination(viewModel, "Where would you like to go?")
        }) { padding ->
            Column(modifier = Modifier.padding(padding)) {
                SearchListDestination(viewModel, onItemClick)
            }
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
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        BasicTextField(
            value = query,
            onValueChange = {
                query = it
                coroutineScope.launch {
                    searchDestinationViewModel.performQuery(it)
                }
            },
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
                    imageVector = Icons.Default.LocationCity,
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = "Search"
                )
                Spacer(modifier = Modifier.width(10.dp))
                Box {
                    if (query.isEmpty()) {
                        Text(
                            text = text,
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        coroutineScope.launch {
                            searchDestinationViewModel.loadDestinations()
                        }
                    } else
                        coroutineScope.launch {
                            searchDestinationViewModel.performQuery(query)
                        }
                    Spacer(modifier = Modifier.width(width = 18.dp))
                    innerTextField()
                }
            }
        }
        Spacer(Modifier.height(10.dp))
    }
}

@Composable
fun SearchListItemDestination(
    destinationItem: DestinationTraits,
    modifier: Modifier = Modifier,
    onItemClick: (String) -> Unit
) {
    Surface(
        modifier
            .padding(vertical = 5.dp, horizontal = 0.dp)
            .clip(MaterialTheme.shapes.medium)
    ) {
        Row {
            Column(modifier = Modifier
                .background(White)
                .height(70.dp)
                .padding(15.dp)
                .clickable {
                    onItemClick(destinationItem.name)
                }
                .fillMaxWidth()){
                Text(
                    text = destinationItem.name,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = destinationItem.country.uppercase(),
                    style = MaterialTheme.typography.bodySmall,
                    color = Grey,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start)
                )
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
                SearchListItemDestination(destinationItem
                ) { fromDestination ->
                    onItemClick(fromDestination)
                }
            }
        }
        if(list==null){
            item {
                for(i in 0..10) {
                    ShimmerItem()
                }
            }
        }
    }
}