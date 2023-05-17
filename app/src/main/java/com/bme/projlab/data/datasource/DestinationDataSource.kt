package com.bme.projlab.data.datasource

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.TravelExplore
import androidx.compose.material.icons.filled.TripOrigin
import com.bme.projlab.domain.model.element.Destination
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

//Todo: delete this file
class DestinationDataSource @Inject constructor(
    firebaseFirestore: FirebaseFirestore
) : DataSource<ArrayList<Destination>, String, Destination?> {

    override fun load(): ArrayList<Destination> {
        return arrayListOf(
            Destination("Nice", null, picture = Icons.Default.TravelExplore),
            Destination("Naples", null, picture = Icons.Default.TripOrigin),
            Destination("Budapest", null, picture = Icons.Default.TripOrigin),
            Destination("Malta", null, picture = Icons.Default.TripOrigin),
            Destination("Paris", null, picture = Icons.Default.TripOrigin)
        )
    }

    override fun get(id: String): Destination? {
        val destinations =  load()
        for (destination: Destination in destinations) {
            if(destination.name == id){
                return destination
            }
        }
        return null
    }

    fun getAll(): ArrayList<Destination> {
        return load()
    }
}