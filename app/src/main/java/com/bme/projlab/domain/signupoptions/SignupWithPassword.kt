package com.bme.projlab.domain.signupoptions

import com.bme.projlab.domain.repository.SignupRepository

class SignupWithPassword (private val repository: SignupRepository) {
    suspend operator fun invoke(
        email: String,
        password: String
    ) = repository.signupWithPassword(email, password)
}
