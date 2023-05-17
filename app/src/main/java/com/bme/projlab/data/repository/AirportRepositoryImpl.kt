package com.bme.projlab.data.repository

import com.bme.projlab.data.datasource.AirportDataSource
import com.bme.projlab.data.datasource.FlightDataSource
import com.bme.projlab.domain.model.element.Airports
import com.bme.projlab.domain.repository.AirportRepository
import com.bme.projlab.domain.repository.SearchResultsRepository
import javax.inject.Inject

class AirportRepositoryImpl @Inject constructor(
    private val airportDataSource: AirportDataSource,
): AirportRepository {

    override suspend fun loadAirports(city: String): ArrayList<Airports.Airport> {
        return airportDataSource.loadAirports(city)
    }

}
