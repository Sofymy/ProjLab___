package com.bme.projlab.ui.screens

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bme.projlab.domain.model.element.Trip
import com.bme.projlab.domain.viewmodel.BadgesViewModel
import com.bme.projlab.domain.viewmodel.HomeViewModel
import com.bme.projlab.domain.viewmodel.ReceivedTripsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("CoroutineCreationDuringComposition", "MutableCollectionMutableState")
@Composable
fun BadgesScreen(viewModel: BadgesViewModel = hiltViewModel()) {
    val badges = remember { mutableStateOf<ArrayList<String>>(ArrayList()) }

    LaunchedEffect(badges) {
        badges.value = viewModel.loadBadges()
    }

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        badges.value.let { it ->
            items(
                items = it.toArray(),
                itemContent = {
                    if (it != null) {
                        BadgeItem(badge = it as String)
                    }
                })
        }
    }
}

@Composable
fun BadgeItem(badge: String) {
    androidx.compose.material.Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .fillMaxWidth()
            .clickable {
                badge.let {
                    it.let { it1 -> Log.d("trip", it1.toString()) }
                }
            },
        elevation = 2.dp,
        backgroundColor = Color.White,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))

    ) {
        Row {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterVertically)) {
                Text(badge)
            }
        }
    }
}
