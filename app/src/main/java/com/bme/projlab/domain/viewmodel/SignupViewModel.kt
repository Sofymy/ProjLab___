package com.bme.projlab.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bme.projlab.domain.model.response.SignupResponse
import com.bme.projlab.domain.signupoptions.SignupOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor(
    private val signupOptions: SignupOptions
) : ViewModel(){
    private var signupResponse by mutableStateOf<SignupResponse>(SignupResponse.Loading)

    fun signupWithPassword(email: String, password: String) = viewModelScope.launch {
        signupResponse = SignupResponse.Loading
        signupResponse = signupOptions.signupWithPassword(email, password)
    }

    fun signupWithGoogle() = viewModelScope.launch {
        signupOptions.signupWithGoogle
    }

    fun signupWithFacebook() = viewModelScope.launch {
        signupOptions.signupWithFacebook
    }

}
