package com.bme.projlab.data.repository

import com.bme.projlab.domain.model.response.SignupResponse
import com.bme.projlab.domain.repository.SignupRepository
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class SignupRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
) : SignupRepository {
    override suspend fun signupWithPassword(email: String, password: String): SignupResponse {
        return try {
            firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            SignupResponse.Success
        } catch (e: Exception) {
            SignupResponse.Error
        }
    }

    override suspend fun signupWithGoogle() {

    }

    override suspend fun signupWithFacebook() {
    }
}