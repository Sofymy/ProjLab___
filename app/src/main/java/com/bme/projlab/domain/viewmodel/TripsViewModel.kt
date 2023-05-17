package com.bme.projlab.domain.viewmodel

import android.annotation.SuppressLint
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.bme.projlab.data.repository.UserRepositoryImpl
import com.bme.projlab.domain.model.element.Trip
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TripsViewModel @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl
) : ViewModel(){
    @SuppressLint("MutableCollectionMutableState")
    val trips: MutableState<ArrayList<Trip>> = mutableStateOf(ArrayList())

    suspend fun getHeartedTrips(): ArrayList<Trip> {
        return userRepositoryImpl.loadHeartedTrips()
    }

    fun heartTrip(trip: Trip) {
        userRepositoryImpl.heartTrip(trip)
    }

    fun unHeartTrip(trip: Trip) {
        userRepositoryImpl.unHeartTrip(trip)
    }

    suspend fun shareTrip(trip: Trip, username: String) {
        userRepositoryImpl.shareTrip(trip, username)
    }

}