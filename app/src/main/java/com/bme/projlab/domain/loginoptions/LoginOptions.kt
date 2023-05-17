package com.bme.projlab.domain.loginoptions

data class LoginOptions(
    val loginWithPassword: LoginWithPassword,
    val loginWithGoogle: LoginWithGoogle,
    val loginWithFacebook: LoginWithFacebook,
    val loggedInUser: LoginImmediately
)