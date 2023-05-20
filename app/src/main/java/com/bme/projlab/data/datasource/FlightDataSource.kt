package com.bme.projlab.data.datasource

import android.util.Log
import com.bme.projlab.data.network.ApiInterface
import com.bme.projlab.domain.model.element.Flights.ReturnFlight
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FlightDataSource(
    private val apiInterface: ApiInterface
): DataSource {

    suspend fun getFlights(origin: String,
                           destination: String,
                           date: String,
                           returnDate: String): ArrayList<ReturnFlight>{
        val response = apiInterface.getFlights(origin, destination, date, returnDate)
        val returnFlights: ArrayList<ReturnFlight> = ArrayList()
        if (response.isSuccessful) {
            val data = response.body()?.data
            if (data != null) {
                for(i in data){
                    //NO STOPS
                    if (i.returnFlight?.get(0)?.let {
                            checkFlights(it,
                                i.returnFlight!![1],
                                returnFlights)
                        } == true){
                        returnFlights.add(i)
                    }
                }
            }
        }
        else {
            Log.e("error", response.code().toString())
        }
        return returnFlights
    }

    private fun checkFlights(
        origin: ReturnFlight.Flight,
        destination: ReturnFlight.Flight,
        returnFlights: ArrayList<ReturnFlight>
    ): Boolean {
        if(origin.stop_count == 0 && destination.stop_count == 0) {
            for (i in returnFlights) {
                if (origin.departure == i.returnFlight?.get(0)?.departure
                    && destination.departure == i.returnFlight?.get(1)?.departure
                ) {
                    return false
                }
            }
            return true
        }
        return false
    }
}