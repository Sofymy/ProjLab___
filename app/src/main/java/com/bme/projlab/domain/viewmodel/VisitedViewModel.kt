package com.bme.projlab.domain.viewmodel

import androidx.lifecycle.ViewModel
import com.bme.projlab.data.repository.UserRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VisitedViewModel @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl
) : ViewModel(){

    suspend fun getVisitedLocations(): ArrayList<String> {
        return userRepositoryImpl.loadVisitedLocations()
    }

    fun addVisitedLocation(location: String) {
        userRepositoryImpl.addVisitedLocation(location)
    }

}