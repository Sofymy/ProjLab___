package com.bme.projlab.data.repository

import com.bme.projlab.domain.model.element.Destination
import com.bme.projlab.domain.model.element.DestinationTraits
import com.bme.projlab.domain.repository.DestinationRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class DestinationRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : DestinationRepository {

    override suspend fun loadDestinations(): ArrayList<DestinationTraits> {
        return firebaseFirestore.collection("locationtraits")
            .get().await().toObjects(DestinationTraits::class.java) as ArrayList<DestinationTraits>
    }
}