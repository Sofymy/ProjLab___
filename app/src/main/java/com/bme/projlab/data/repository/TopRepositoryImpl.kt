package com.bme.projlab.data.repository

import com.bme.projlab.domain.repository.TopRepository
import com.bme.projlab.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class TopRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
): TopRepository {

    override fun signOut() {
        firebaseAuth.signOut()
    }

}