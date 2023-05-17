package com.bme.projlab.domain.viewmodel

import androidx.lifecycle.ViewModel
import com.bme.projlab.data.repository.SearchResultsRepositoryImpl
import com.bme.projlab.data.repository.UserRepositoryImpl
import com.bme.projlab.domain.model.element.Flights
import com.bme.projlab.domain.model.element.Trip
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchResultsViewModel@Inject constructor(
    private val searchResultsRepositoryImpl: SearchResultsRepositoryImpl,
    private val userRepositoryImpl: UserRepositoryImpl
) : ViewModel() {
    suspend fun searchReturnFlights(
        fromDestination: String,
        toDestination: String,
        fromDate: String,
        toDate: String
    ): ArrayList<Flights.ReturnFlight> {
        return searchResultsRepositoryImpl.searchReturnFlights(
            fromDestination,
            toDestination,
            fromDate,
            toDate
        )
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