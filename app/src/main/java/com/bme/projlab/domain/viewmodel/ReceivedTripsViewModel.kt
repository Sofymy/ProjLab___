package com.bme.projlab.domain.viewmodel

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.bme.projlab.data.repository.ReceivedTripsRepositoryImpl
import com.bme.projlab.data.repository.UserRepositoryImpl
import com.bme.projlab.domain.model.element.Trip
import com.bme.projlab.domain.model.response.TripResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReceivedTripsViewModel @Inject constructor(
    private val receivedTripsRepositoryImpl: ReceivedTripsRepositoryImpl
) : ViewModel(){

    @SuppressLint("MutableCollectionMutableState")
    var trips = ArrayList<Trip>()

    suspend fun loadReceivedTrips(): ArrayList<Trip> {
        trips = receivedTripsRepositoryImpl.loadReceivedTrips()
        return receivedTripsRepositoryImpl.loadReceivedTrips()
    }

}