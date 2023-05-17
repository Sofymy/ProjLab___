package com.bme.projlab.domain.repository

import com.bme.projlab.domain.model.response.SignupResponse

interface SignupRepository {
    suspend fun signupWithPassword(email: String, password: String): SignupResponse
    suspend fun signupWithGoogle()
    suspend fun signupWithFacebook()
}