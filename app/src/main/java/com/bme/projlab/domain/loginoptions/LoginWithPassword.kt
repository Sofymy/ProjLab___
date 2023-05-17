package com.bme.projlab.domain.loginoptions

import com.bme.projlab.domain.repository.LoginRepository

class LoginWithPassword (private val repository: LoginRepository) {
    suspend operator fun invoke(
            email: String,
            password: String
        ) = repository.loginWithPassword(email, password)
}
