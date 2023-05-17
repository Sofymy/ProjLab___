package com.bme.projlab.domain.signupoptions

import com.bme.projlab.domain.loginoptions.LoginWithFacebook
import com.bme.projlab.domain.loginoptions.LoginWithGoogle
import com.bme.projlab.domain.loginoptions.LoginWithPassword

data class SignupOptions(
    val signupWithPassword: SignupWithPassword,
    val signupWithGoogle: SignupWithGoogle,
    val signupWithFacebook: SignupWithFacebook
)