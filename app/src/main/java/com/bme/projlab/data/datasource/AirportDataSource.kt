package com.bme.projlab.data.datasource

import android.util.Log
import com.bme.projlab.data.network.ApiInterface
import com.bme.projlab.domain.model.element.Airports
import javax.inject.Inject

class AirportDataSource @Inject constructor(
    private val apiInterface: ApiInterface
): DataSource{
    suspend fun loadAirports(city: String): ArrayList<Airports.Airport> {
        val response = apiInterface.getAirports(city)
        val airportCodes: ArrayList<String> = ArrayList()
        val airports = ArrayList<Airports.Airport>()
        if (response.isSuccessful) {
            val data = response.body()?.data
            if (data != null) {
                for(airport in data){
                    if(checkAirportCode(airportCodes,airport)) {
                        airport.placeId?.let { airportCodes.add(it) }
                        airports.add(airport)
                    }
                }
            }
        }
        else {
            Log.e("error", response.code().toString())
        }
        return airports
    }

    private fun checkAirportCode(airportCodes: ArrayList<String>, airport: Airports.Airport): Boolean{
        if(!airport.placeId.isNullOrEmpty()
            && (airport.placeId != "")
            && (airport.countryId != "US")
            && !airportCodes.contains(airport.placeId)
        ){
            return true
        }
        return false
    }

}