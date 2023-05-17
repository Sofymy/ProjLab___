package com.bme.projlab.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bme.projlab.domain.loginoptions.LoginOptions
import com.bme.projlab.domain.model.response.LoginResponse
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginOptions: LoginOptions
) : ViewModel(){
    var loginResponse by mutableStateOf<LoginResponse>(LoginResponse.Loading)

    fun loginWithPassword(email: String, password: String) = viewModelScope.launch{
        loginResponse = LoginResponse.Loading
        loginOptions.loginWithPassword(email, password).let { result ->
            loginResponse = when(result){
                is LoginResponse.Loading -> {
                    LoginResponse.Loading
                }
                is LoginResponse.Error -> {
                    LoginResponse.Error
                }
                is LoginResponse.Success -> {
                    LoginResponse.Success
                }
                is LoginResponse.SuccessFirst -> {
                    LoginResponse.SuccessFirst
                }
            }
        }
    }

    fun loginWithGoogle() = viewModelScope.launch {
        loginOptions.loginWithGoogle()
    }

    fun loginWithFacebook() = viewModelScope.launch {
        loginOptions.loginWithFacebook()
    }

    fun currentUser(): FirebaseUser? {
        return loginOptions.loggedInUser()
    }

}