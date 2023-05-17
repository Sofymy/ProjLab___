package com.bme.projlab.domain.repository

import com.bme.projlab.domain.model.element.Airports

interface AirportRepository {

    suspend fun loadAirports(city: String): ArrayList<Airports.Airport>
}
