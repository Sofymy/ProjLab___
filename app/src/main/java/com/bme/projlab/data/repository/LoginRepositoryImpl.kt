package com.bme.projlab.data.repository

import android.util.Log
import com.bme.projlab.domain.model.response.LoginResponse
import com.bme.projlab.domain.repository.LoginRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) :LoginRepository{

    override suspend fun loginWithPassword(email: String, password: String): LoginResponse {
        return try {
            firebaseAuth.signInWithEmailAndPassword(email, password).await()
            if(hasUsername()){
                LoginResponse.Success
            }
            else LoginResponse.SuccessFirst
        } catch (e: Exception) {
            LoginResponse.Error
        }
    }

    private fun hasUsername(): Boolean{
        if(firebaseAuth.currentUser?.displayName.isNullOrEmpty()){
            return false
        }
        return true
    }

    override suspend fun loginWithGoogle() {
    }

    override suspend fun loginWithFacebook() {
    }
    override suspend fun sendRecoveryEmail(email: String) {
        firebaseAuth.sendPasswordResetEmail(email).await()
    }

    override fun currentUser(): FirebaseUser? {
        return firebaseAuth.currentUser
    }

    override fun signOut(){
        firebaseAuth.signOut()
    }

}