package com.bme.projlab.domain.repository

import com.bme.projlab.domain.model.element.Trip
import com.bme.projlab.domain.model.response.TripResponse
import kotlinx.coroutines.flow.Flow

interface ReceivedTripsRepository {

    suspend fun loadReceivedTrips(): ArrayList<Trip>

}
