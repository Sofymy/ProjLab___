package com.bme.projlab.domain.loginoptions

import com.bme.projlab.domain.repository.LoginRepository

class LoginWithGoogle (private val repository: LoginRepository) {
    suspend operator fun invoke() = repository.loginWithGoogle()
}

