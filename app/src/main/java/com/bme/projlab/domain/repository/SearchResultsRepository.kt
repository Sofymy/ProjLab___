package com.bme.projlab.domain.repository

import com.bme.projlab.domain.model.element.Flights
import com.bme.projlab.domain.model.element.Trip

interface SearchResultsRepository {
    suspend fun searchReturnFlights(fromDestination: String,
                                    toDestination: String,
                                    fromDate: String,
                                    toDate: String): ArrayList<Flights.ReturnFlight>

}