package com.bme.projlab.domain.viewmodel

import androidx.lifecycle.ViewModel
import com.bme.projlab.data.repository.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val userRepositoryImpl: UserRepositoryImpl
) : ViewModel(){
    fun getUsername(): String? {
        return userRepositoryImpl.getUsername()
    }

}