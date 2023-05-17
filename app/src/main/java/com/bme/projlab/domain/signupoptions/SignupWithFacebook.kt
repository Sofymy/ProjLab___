package com.bme.projlab.domain.signupoptions

import com.bme.projlab.domain.repository.SignupRepository

class SignupWithFacebook(private val repository: SignupRepository) {
    suspend operator fun invoke() = repository.signupWithFacebook()
}
