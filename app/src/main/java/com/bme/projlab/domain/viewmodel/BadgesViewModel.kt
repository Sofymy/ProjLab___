package com.bme.projlab.domain.viewmodel

import androidx.lifecycle.ViewModel
import com.bme.projlab.data.repository.BadgesRepositoryImpl
import com.bme.projlab.data.repository.UserRepositoryImpl
import com.bme.projlab.domain.modificationoptions.ModificationOptions
import com.bme.projlab.domain.repository.BadgeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BadgesViewModel @Inject constructor(
    private val userRepositoryImpl: UserRepositoryImpl
) : ViewModel(){

    suspend fun loadBadges(): ArrayList<String>{
        return userRepositoryImpl.loadBadges()
    }

}