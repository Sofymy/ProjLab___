package com.bme.projlab.domain.loginoptions

import com.bme.projlab.domain.repository.LoginRepository

class LoginImmediately(private val repository: LoginRepository) {
    operator fun invoke() = repository.currentUser()
}