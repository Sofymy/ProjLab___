package com.bme.projlab.data.datasource

import com.bme.projlab.domain.model.element.Trip
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class TripDataSource @Inject constructor(
    firebaseFirestore: FirebaseFirestore
) : DataSource<ArrayList<Trip?>, Int, Trip?> {

    override fun load(): ArrayList<Trip?> {
        return arrayListOf(
            Trip(1, null, null, null, null),
            Trip(2, null, null, null, null),
        )
    }

    override fun get(id: Int): Trip? {
        val trips =  load()
        for (trip: Trip? in trips) {
            if (trip != null) {
                if(trip.tripId == id){
                    return trip
                }
            }
        }
        return null
    }
}