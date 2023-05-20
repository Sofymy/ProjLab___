package com.bme.projlab.ui.screens.tripsscreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FolderShared
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.model.element.Trip
import com.bme.projlab.domain.viewmodel.TripsViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition", "MutableCollectionMutableState")
@Composable
fun TripsScreen(
    viewModel: TripsViewModel = hiltViewModel()
) {

    val trips = remember { mutableStateOf(viewModel.trips.value) }

    LaunchedEffect(trips) {
        trips.value = viewModel.getHeartedTrips()
    }

    Column {
        Spacer(modifier = Modifier.height(10.dp))
        BadgedBox(badge = { Badge { Text("8") } }) {
                Button(onClick = { /*TODO*/ }) {
                    Text("Received trips")
                }
        }

        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
        ) {
            items(
                items = trips.value.toArray(),
                itemContent = {
                    if (it != null) {
                        TripListItem(trip = it as Trip, viewModel)
                    }
                })
        }
    }

}

@Composable
fun TripListItem(trip: Trip, viewModel: TripsViewModel) {
    HeartAnimation(trip = trip, viewModel = viewModel)
    androidx.compose.material.Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                trip.toDestination.let {
                    if (it != null) {
                        it.name?.let { it1 -> Log.d("trip", it1) }
                    }
                }
            },
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Row {
            DestinationImage(trip = trip)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)) {
                trip.toDestination?.let { it.name?.let { it1 -> Text(text = it1, style = typography.h6) } }
                trip.flight?.let { it.returnFlight?.get(0)
                    ?.let { it1 -> it1.arrival?.let { it2 -> Text(text = it2, style = typography.caption) } } }
                trip.flight?.let { it.returnFlight?.get(1)
                    ?.let { it1 -> it1.departure?.let { it2 -> Text(text = it2, style = typography.caption) } } }
            }
        }
    }
}

@Composable
private fun DestinationImage(trip: Trip) {
    trip.toDestination?.let {
        it.picture?.let { it1 ->
            Image(
                it1,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(8.dp)
                .size(84.dp)
                .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
        )
        }
    }
}

@Composable
fun HeartAnimation(trip: Trip, viewModel: TripsViewModel) {
    val interactionSource = MutableInteractionSource()
    val coroutineScope = rememberCoroutineScope()

    var enabled by remember {
        mutableStateOf(true)
    }

    val scale = remember {
        androidx.compose.animation.core.Animatable(1f)
    }

    Icon(
        imageVector = Icons.Filled.Favorite,
        contentDescription = "Unlike the trip",
        tint = if (enabled) Color.Red else Color.LightGray,
        modifier = Modifier
            .scale(scale = scale.value)
            .size(size = 30.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) {
                enabled = !enabled
                viewModel.unHeartTrip(trip)
                coroutineScope.launch {
                    scale.animateTo(
                        0.8f,
                        animationSpec = tween(100),
                    )
                    scale.animateTo(
                        1f,
                        animationSpec = tween(100),
                    )
                }
            }
    )
}