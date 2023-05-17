package com.bme.projlab.data.repository

import android.util.Log
import com.bme.projlab.domain.model.element.Trip
import com.bme.projlab.domain.model.response.TripResponse
import com.bme.projlab.domain.repository.ReceivedTripsRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class ReceivedTripsRepositoryImpl @Inject constructor(
    val firebaseAuth: FirebaseAuth,
    val firebaseFirestore: FirebaseFirestore
) : ReceivedTripsRepository {

    var receivedTripIds = ArrayList<String>()
    private val receivedTrips = ArrayList<Trip>()

    override suspend fun loadReceivedTrips(): ArrayList<Trip> {
        firebaseAuth.currentUser?.uid?.let { it ->
            receivedTripIds = firebaseFirestore.collection("users").document(it)
                .get().await().get("receivedTrips") as ArrayList<String>
        }
        (receivedTripIds).map { trip ->
            val receivedTrip = firebaseFirestore.collection("trips").document(trip)
                .get().await().toObject(Trip::class.java) as Trip
            receivedTrips.add(receivedTrip)
        }
        return receivedTrips
    }

}
