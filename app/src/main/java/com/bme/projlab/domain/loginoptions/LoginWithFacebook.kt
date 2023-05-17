package com.bme.projlab.domain.loginoptions

import com.bme.projlab.domain.repository.LoginRepository

class LoginWithFacebook(private val repository: LoginRepository) {
    suspend operator fun invoke() = repository.loginWithFacebook()
}
