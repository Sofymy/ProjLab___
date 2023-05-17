package com.bme.projlab.domain.repository

import com.bme.projlab.domain.model.element.Trip

interface UserRepository {
    fun heartTrip(trip: Trip)
    fun unHeartTrip(trip: Trip)
    suspend fun shareTrip(trip: Trip, username: String)
    fun loadReceivedTrips()
    suspend fun loadHeartedTrips(): ArrayList<Trip>
    suspend fun loadVisitedLocations(): ArrayList<String>
    fun addVisitedLocation(location: String)
}
