package com.bme.projlab.ui.screens.tripsscreen

import android.annotation.SuppressLint
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.model.element.Trip
import com.bme.projlab.domain.viewmodel.TripsViewModel
import com.bme.projlab.ui.theme.Blue
import com.bme.projlab.ui.theme.Grey
import com.bme.projlab.ui.theme.Purple
import com.bme.projlab.ui.theme.White
import com.valentinilk.shimmer.shimmer
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition", "MutableCollectionMutableState")
@Composable
fun TripsScreen(
    viewModel: TripsViewModel = hiltViewModel(),
    navigateToReceived: () -> Unit
) {

    val trips = remember { mutableStateOf(viewModel.trips.value) }

    LaunchedEffect(trips) {
        trips.value = viewModel.getHeartedTrips()
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
    )
    {
        Spacer(modifier = Modifier.height(10.dp))
        androidx.compose.material3.Button(
            onClick = {
                navigateToReceived()
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue
            ),
            modifier = Modifier.shimmer()
        ) {
            Text(
                "Received trips",
                style = MaterialTheme.typography.bodyMedium,
                color = White
            )
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(weight =1f, fill = false)) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                "Your saved trips",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(20.dp))
            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                items(
                    items = trips.value.toArray(),
                    itemContent = {
                        if (it != null) {
                            TripListItem(trips, trip = it as Trip, viewModel)
                        }
                    })
            }
        }
    }
}

@Composable
fun TripListItem(trips: MutableState<ArrayList<Trip>>, trip: Trip, viewModel: TripsViewModel) {
    var separatorOffsetY by remember { mutableStateOf<Float?>(null) }
    val cornerRadius = 20.dp
    androidx.compose.material.Card(
        shape = RoundedCutoutShape(separatorOffsetY, cornerRadius),
        backgroundColor = Color.White,
        modifier = Modifier.padding(10.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(modifier = Modifier
                .height(200.dp)
                .padding(15.dp)){
                Column() {
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "FLY OUT",
                        style = MaterialTheme.typography.bodySmall,
                        color = Grey,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.Start)
                    )
                    Text("Departure: ${
                        trip.flight?.returnFlight?.get(0)?.departure
                            ?.replace("T", " ")
                    }", style = MaterialTheme.typography.bodyMedium)
                    Text("Arrival: ${
                        trip.flight?.returnFlight?.get(0)?.arrival
                            ?.replace("T", " ")
                    }", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = "FLY BACK",
                        style = MaterialTheme.typography.bodySmall,
                        color = Grey,
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentWidth(Alignment.Start)
                    )
                    Text("Departure: ${trip.flight?.returnFlight?.get(1)?.departure
                        ?.replace("T", " ")
                    }", style = MaterialTheme.typography.bodyMedium)
                    Text("Arrival: ${trip.flight?.returnFlight?.get(1)?.arrival
                        ?.replace("T", " ")
                    }", style = MaterialTheme.typography.bodyMedium)
                }
            }
            Box(
                Modifier
                    .padding(horizontal = cornerRadius)
                    .height(1.dp)
                    .requiredWidth(250.dp)
                    .background(Color.Gray, shape = DottedShape(step = 20.dp))
                    .onGloballyPositioned {
                        separatorOffsetY = it.boundsInParent().center.y
                    }
            )
            Box(modifier = Modifier.height(80.dp)
                .padding(15.dp)
            , contentAlignment = Alignment.Center
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        "Trip to ${trip.flight?.returnFlight?.get(1)?.destination?.name}",
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold
                    )
                    Text("${trip.flight?.price?.amount} Eur", style = MaterialTheme.typography.bodyMedium)
                }
            }
            HeartAnimation(trips, trip = trip, viewModel = viewModel)
            Spacer(modifier = Modifier.height(10.dp))
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
fun HeartAnimation(trips: MutableState<ArrayList<Trip>>, trip: Trip, viewModel: TripsViewModel) {
    val interactionSource = MutableInteractionSource()
    val coroutineScope = rememberCoroutineScope()

    var enabled by remember {
        mutableStateOf(true)
    }

    val scale = remember {
        androidx.compose.animation.core.Animatable(1f)
    }

    Icon(
        imageVector = Icons.Filled.HeartBroken,
        contentDescription = "Unlike the trip",
        tint = if (enabled) Purple else Color.LightGray,
        modifier = Modifier
            .scale(scale = scale.value)
            .size(size = 30.dp)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                trips.value.remove(trip)
            ) {
                enabled = !enabled
                trips.value.remove(trip)
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

class RoundedCutoutShape(
    private val offsetY: Float?,
    private val cornerRadiusDp: Dp,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density,
    ) = Outline.Generic(run path@{
        val cornerRadius = with(density) { cornerRadiusDp.toPx() }
        val rect = Rect(Offset.Zero, size)
        val mainPath = Path().apply {
            addRoundRect(RoundRect(rect, CornerRadius(cornerRadius)))
        }
        if (offsetY == null) return@path mainPath
        val cutoutPath = Path().apply {
            val circleSize = Size(cornerRadius, cornerRadius) * 2f
            val visiblePart = 0.25f
            val leftOval = Rect(
                offset = Offset(
                    x = 0 - circleSize.width * (1 - visiblePart),
                    y = offsetY - circleSize.height / 2
                ),
                size = circleSize
            )
            val rightOval = Rect(
                offset = Offset(
                    x = rect.width - circleSize.width * visiblePart,
                    y = offsetY - circleSize.height / 2
                ),
                size = circleSize
            )
            addOval(leftOval)
            addOval(rightOval)
        }
        return@path Path().apply {
            op(mainPath, cutoutPath, PathOperation.Difference)
        }
    })
}

private data class DottedShape(
    val step: Dp,
) : Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ) = Outline.Generic(Path().apply {
        val stepPx = with(density) { step.toPx() }
        val stepsCount = (size.width / stepPx).roundToInt()
        val actualStep = size.width / stepsCount
        val dotSize = Size(width = actualStep / 2, height = size.height)
        for (i in 0 until stepsCount) {
            addRect(
                Rect(
                    offset = Offset(x = i * actualStep, y = 0f),
                    size = dotSize
                )
            )
        }
        close()
    })
}