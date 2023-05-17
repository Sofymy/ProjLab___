package com.bme.projlab.domain.model.response

sealed class UsernameResponse{
    object Loading: UsernameResponse()
    object Success: UsernameResponse()
    object Error: UsernameResponse()
}