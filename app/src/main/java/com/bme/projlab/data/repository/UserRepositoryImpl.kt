package com.bme.projlab.data.repository

import com.bme.projlab.domain.model.element.Trip
import com.bme.projlab.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
): UserRepository {

    override fun heartTrip(trip: Trip) {
        firebaseAuth.uid?.let { firebaseFirestore.collection("users").document(it) }
            ?.update("heartedTrips", FieldValue.arrayUnion(trip.tripId.toString()))

        firebaseFirestore.collection("trips").document(trip.tripId.toString()).set(trip)
    }

    override fun unHeartTrip(trip: Trip) {
        firebaseAuth.uid?.let { firebaseFirestore.collection("users").document(it) }
            ?.update("heartedTrips", FieldValue.arrayRemove(trip.tripId.toString()))
    }

    override suspend fun shareTrip(trip: Trip, username: String){
        var uId = ""
        val data = hashMapOf(
            "username" to firebaseAuth.currentUser?.displayName,
            "trip" to trip.tripId.toString()
        )
        firebaseFirestore.collection("trips").document(trip.tripId.toString()).set(trip)
        firebaseFirestore.collection("usernames").document(username)
            .get()
            .addOnSuccessListener { result ->
                uId = result.data?.get("uid").toString()
            }.addOnSuccessListener {
                firebaseFirestore.collection("users").document(uId)
                    .update("receivedTrips", FieldValue.arrayUnion(data))
            }
    }

    override fun loadReceivedTrips() {
        TODO("Not yet implemented")
    }

    override suspend fun loadVisitedLocations(): ArrayList<String>{
        var visitedLocations = ArrayList<String>()
        firebaseAuth.currentUser?.uid?.let { it ->
            visitedLocations = firebaseFirestore.collection("users").document(it)
                .get().await().get("visitedLocations") as ArrayList<String>
        }
        if(visitedLocations.size.mod(5) == 0){
            addBadge(visitedLocations.size.toString()+"visited")
        }
        return visitedLocations
    }

    override fun addVisitedLocation(location: String) {
        firebaseAuth.uid?.let { firebaseFirestore.collection("users").document(it) }
            ?.update("visitedLocations", FieldValue.arrayUnion(location))
    }

    private fun addBadge(name: String) {
        firebaseAuth.uid?.let { firebaseFirestore.collection("users").document(it) }
            ?.update("badges", FieldValue.arrayUnion(name))
    }

    suspend fun loadBadges(): ArrayList<String> {
        var badges = ArrayList<String>()
        firebaseAuth.currentUser?.uid?.let { it ->
            badges = firebaseFirestore.collection("users").document(it)
                .get().await().get("badges") as ArrayList<String>
        }
        return badges
    }

    override suspend fun loadHeartedTrips(): ArrayList<Trip> {
        var heartedTripIds = ArrayList<String>()
        val heartedTrips = ArrayList<Trip>()
        firebaseAuth.currentUser?.uid?.let { it ->
            heartedTripIds = firebaseFirestore.collection("users").document(it)
                .get().await().get("heartedTrips") as ArrayList<String>
        }
        (heartedTripIds).map { trip ->
            val heartedTrip = firebaseFirestore.collection("trips").document(trip)
                .get().await().toObject(Trip::class.java) as Trip
            heartedTrips.add(heartedTrip)
        }
        return heartedTrips
    }

    fun getUsername(): String? {
        return firebaseAuth.currentUser?.displayName
    }

}
