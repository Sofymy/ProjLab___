package com.bme.projlab.domain.model.response

sealed class SignupResponse{
    object Loading: SignupResponse()
    object Success: SignupResponse()
    object Error: SignupResponse()
}