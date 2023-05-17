package com.bme.projlab.data.repository

import com.bme.projlab.domain.model.element.Trip
import com.bme.projlab.domain.repository.BadgeRepository
import com.bme.projlab.domain.repository.ReceivedTripsRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class BadgesRepositoryImpl @Inject constructor(
    val firebaseFirestore: FirebaseFirestore,
    val firebaseAuth: FirebaseAuth
) : BadgeRepository {


}