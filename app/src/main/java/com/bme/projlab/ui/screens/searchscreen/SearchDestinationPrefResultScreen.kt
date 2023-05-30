package com.bme.projlab.ui.screens.searchscreen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.model.element.DestinationTraits
import com.bme.projlab.domain.viewmodel.SearchDestinationPreferencesViewModel
import com.bme.projlab.ui.theme.Grey
import com.bme.projlab.ui.theme.White
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition", "MutableCollectionMutableState")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun SearchDestinationPrefResultScreen(
    viewModel: SearchDestinationPreferencesViewModel = hiltViewModel(),
    capital: Boolean,
    historical: Boolean,
    warm: Boolean,
    first: Boolean,
    navigateToAirport: (String) -> Unit,
    fromAirport: String,
) {

    val coroutineScope = rememberCoroutineScope()
    val results = remember { mutableStateOf<ArrayList<DestinationTraits>?>(null) }

    coroutineScope.launch {
        results.value = viewModel.searchByAttributes(
            capital,
            historical,
            warm,
            first
        )
    }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(15.dp)
    ) {
        Phases(0.6f)
        LazyColumn(
        ) {
            results.value?.let { it ->
                items(
                    items = it.toArray()
                ) { it ->
                    if (it != null) {
                        val dest = it as DestinationTraits
                        DestinationListItem(destinationTraits = dest) {
                            navigateToAirport(it)
                        }
                    }
                }
            }
            if (results.value == null) {
                item {
                    for (i in 0..10) {
                        ShimmerItem()
                    }
                }
            }
        }
    }
}

@Composable
fun DestinationListItem(
    destinationTraits: DestinationTraits,
    navigateToToAirport: (String) -> Unit
) {
    androidx.compose.material.Card(
        modifier = Modifier
            .padding(horizontal = 0.dp, vertical = 5.dp)
            .fillMaxWidth(),
        elevation = 0.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Row {
            Column(modifier = Modifier
                .background(White)
                .height(70.dp)
                .padding(15.dp)
                .clickable {
                    navigateToToAirport(destinationTraits.name)
                }
                .fillMaxWidth()){
                Text(
                    text = destinationTraits.name,
                    style = androidx.compose.material3.MaterialTheme.typography.bodyMedium,
                    color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start)
                )
                Text(
                    text = destinationTraits.country,
                    style = androidx.compose.material3.MaterialTheme.typography.bodySmall,
                    color = Grey,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.Start)
                )
            }
        }
    }
}
