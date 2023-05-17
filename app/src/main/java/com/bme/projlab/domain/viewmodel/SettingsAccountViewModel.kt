package com.bme.projlab.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bme.projlab.domain.model.response.UsernameResponse
import com.bme.projlab.domain.modificationoptions.ModificationOptions
import com.bme.projlab.domain.modificationoptions.ModifyPassword
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsAccountViewModel @Inject constructor(
    private val modificationOptions: ModificationOptions
) : ViewModel(){

    suspend fun modifyPassword(password: String){
        modificationOptions.modifyPassword(password)
    }

}