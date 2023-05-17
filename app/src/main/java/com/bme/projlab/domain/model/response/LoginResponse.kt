package com.bme.projlab.domain.model.response

sealed class LoginResponse{
    object Loading: LoginResponse()
    object Success: LoginResponse()
    object SuccessFirst: LoginResponse()
    object Error: LoginResponse()
}