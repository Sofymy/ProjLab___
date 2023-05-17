package com.bme.projlab.data.repository

import com.bme.projlab.data.datasource.FlightDataSource
import com.bme.projlab.domain.model.element.Flights
import com.bme.projlab.domain.repository.SearchResultsRepository
import javax.inject.Inject

class SearchResultsRepositoryImpl @Inject constructor(
    private val flightDataSource: FlightDataSource
): SearchResultsRepository {

    override suspend fun searchReturnFlights(
        fromAirport: String,
        toAirport: String,
        fromDate: String,
        toDate: String,
    ): ArrayList<Flights.ReturnFlight> {
        return flightDataSource.getFlights(
            fromAirport,
            toAirport,
            fromDate,
            toDate
        )
    }

}