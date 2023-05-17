package com.bme.projlab.domain.viewmodel

import androidx.lifecycle.ViewModel
import com.bme.projlab.data.repository.TopRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TopViewModel @Inject constructor(
    private val topRepositoryImpl: TopRepositoryImpl
) : ViewModel(){

    fun signOut() {
        topRepositoryImpl.signOut()
    }

}
