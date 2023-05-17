package com.bme.projlab.ui.screens.searchscreen

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.model.element.Flights
import com.bme.projlab.domain.model.element.Destination
import com.bme.projlab.domain.model.element.Trip
import com.bme.projlab.domain.viewmodel.SearchResultsViewModel
import kotlinx.coroutines.launch

@SuppressLint("MutableCollectionMutableState", "CoroutineCreationDuringComposition")
@Composable
fun SearchResultsScreen(
    viewModel: SearchResultsViewModel = hiltViewModel(),
    fromAirport: String,
    toAirport: String,
    fromDate: String,
    toDate: String
) {

    val coroutineScope = rememberCoroutineScope()
    val results = remember { mutableStateOf<ArrayList<Flights.ReturnFlight>?>(null) }

    coroutineScope.launch {
        results.value = viewModel.searchReturnFlights(fromAirport,
            toAirport,
            fromDate,
            toDate)
    }
    
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        results.value?.let { it ->
            items(
                items = it.toArray(),
                itemContent = {
                    if (it != null) {
                        val fromDest = Destination(fromAirport)
                        val toDest = Destination(toAirport)
                        val trip = Trip(flight = it as Flights.ReturnFlight?, fromDestination = fromDest, toDestination = toDest)
                        TripListItem(trip = trip, viewModel)
                    }
                })
        }
    }
}

@Composable
fun TripListItem(trip: Trip, viewModel: SearchResultsViewModel) {
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
            HeartAnimation(trip, viewModel)
            ShareAnimation(trip, viewModel)
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)) {
                trip.flight?.let { Text(text = it.price?.amount.toString(), style = MaterialTheme.typography.caption) }
                trip.flight?.let { it.returnFlight?.get(0)
                    ?.let { it1 -> it1.departure?.let { it2 -> Text(text = it2, style = MaterialTheme.typography.caption) } } } }
        }
    }
}

@Composable
fun HeartAnimation(trip: Trip, viewModel: SearchResultsViewModel) {
    val interactionSource = MutableInteractionSource()
    val coroutineScope = rememberCoroutineScope()

    var enabled by remember {
        mutableStateOf(false)
    }

    val scale = remember {
        androidx.compose.animation.core.Animatable(1f)
    }

    Icon(
        imageVector = Icons.Outlined.Favorite,
        contentDescription = "Like the trip",
        tint = if (enabled) Color.Red else Color.LightGray,
        modifier = Modifier
            .scale(scale = scale.value)
            .size(size = 30.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) {
                enabled = !enabled
                if (enabled) viewModel.heartTrip(trip)
                else viewModel.unHeartTrip(trip)
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

@Composable
fun ShareAnimation(trip: Trip, viewModel: SearchResultsViewModel) {
    val interactionSource = MutableInteractionSource()
    val coroutineScope = rememberCoroutineScope()

    var enabled by remember {
        mutableStateOf(false)
    }

    val scale = remember {
        androidx.compose.animation.core.Animatable(1f)
    }

    val dialogState: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    if (dialogState.value) {
        Dialog(
            onDismissRequest = { dialogState.value = false },
            content = {
                CompleteDialogContent("Share trip trip with fellow users", dialogState, "OK") { BodyContent(trip, viewModel) }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) }

    Icon(
        imageVector = Icons.Outlined.Share,
        contentDescription = "Share the trip",
        tint = if (enabled) Color.Red else Color.LightGray,
        modifier = Modifier
            .scale(scale = scale.value)
            .size(size = 30.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
            ) {
                enabled = !enabled
                dialogState.value = true
                //viewModel.shareTrip(trip)
                //viewModel.getUserNames()
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BodyContent(trip: Trip, viewModel: SearchResultsViewModel) {
    Column {
        Text(
            text = "Share",
            fontSize = 22.sp
        )
        val coroutineScope = rememberCoroutineScope()
        var username by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            value = username,
            label = { Text(text = "Enter a username") },
            onValueChange = {
                username = it
            }
        )
        Button(
            onClick={
                coroutineScope.launch {
                    viewModel.shareTrip(trip, username.text)
                }
            }
        ){
            Text("Send")
        }
    }
}

@Composable
fun CompleteDialogContent(
    title: String,
    dialogState: MutableState<Boolean>,
    successButtonText: String,
    content: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxHeight(0.9f)
            .fillMaxWidth(1f),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth(1f)
                .fillMaxHeight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            TitleAndButton(title, dialogState)
            AddBody(content)
            BottomButtons(successButtonText, dialogState = dialogState)
        }
    }
}

@Composable
private fun TitleAndButton(title: String, dialogState: MutableState<Boolean>) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = title, fontSize = 24.sp)
            IconButton(modifier = Modifier.then(Modifier.size(24.dp)),
                onClick = {
                    dialogState.value = false
                }) {
                Icon(
                    Icons.Filled.Close,
                    "contentDescription"
                )
            }
        }
        Divider(color = Color.DarkGray, thickness = 1.dp)
    }
}

@Composable
private fun BottomButtons(successButtonText: String, dialogState: MutableState<Boolean>) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .fillMaxWidth(1f)
            .padding(20.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .width(100.dp)
                .padding(end = 5.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = "Cancel", fontSize = 20.sp)
        }
        Button(
            onClick = {
                dialogState.value = false
            },
            modifier = Modifier.width(100.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(text = successButtonText, fontSize = 20.sp)
        }

    }
}

@Composable
private fun AddBody(content: @Composable () -> Unit) {
    Box(
        modifier = Modifier
            .padding(20.dp)
    ) {
        content()
    }
}

