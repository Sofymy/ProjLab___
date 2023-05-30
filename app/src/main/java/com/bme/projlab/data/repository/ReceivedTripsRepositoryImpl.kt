package com.bme.projlab.data.repository

import android.util.Log
import com.bme.projlab.domain.model.element.ReceivedTrip
import com.bme.projlab.domain.model.element.Trip
import com.bme.projlab.domain.model.response.TripResponse
import com.bme.projlab.domain.repository.ReceivedTripsRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
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

    private val receivedTrips = ArrayList<Trip>()

    override suspend fun loadReceivedTrips(): ArrayList<Trip> {
        val receivedTripIds = ArrayList<String>()
        val trips = ArrayList<Trip>()
        firebaseAuth.currentUser?.uid?.let { it ->
            val receivedTrips = firebaseFirestore.collection("users").document(it)
                .get().await().get("receivedTrips") as ArrayList<Map<String, String>>
            receivedTrips.forEach {
                if(!receivedTripIds.contains(it["trip"])){
                    it["trip"]?.let { it1 -> receivedTripIds.add(it1) }
                }
            }
        }
        (receivedTripIds).map { trip ->
            Log.d("bbbbbb", trip)
            val trip = firebaseFirestore.collection("trips").document(trip)
                .get().await().toObject(Trip::class.java) as Trip
            trips.add(trip)
        }
        return trips
    }


}
