package com.bme.projlab.data.network

import com.bme.projlab.domain.model.element.Airports
import com.bme.projlab.domain.model.element.Flights
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiInterface {
    @GET("searchFlights?")
    suspend fun getFlights(@Query("origin") origin: String,
                           @Query("destination") destination: String,
                           @Query("date") date: String,
                           @Query("returnDate") returnDate: String
    ): Response<Flights>

    @GET("searchAirport?")
    suspend fun getAirports(@Query("query") city: String) : Response<Airports>
}