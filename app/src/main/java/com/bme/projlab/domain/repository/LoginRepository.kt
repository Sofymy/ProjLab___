package com.bme.projlab.domain.repository

import com.bme.projlab.domain.model.response.LoginResponse
import com.google.firebase.auth.FirebaseUser

interface LoginRepository {
    suspend fun loginWithPassword(email: String, password: String): LoginResponse
    suspend fun loginWithGoogle()
    suspend fun loginWithFacebook()
    suspend fun sendRecoveryEmail(email: String)
    fun currentUser(): FirebaseUser?
    fun signOut()
}