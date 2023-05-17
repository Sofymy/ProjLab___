package com.bme.projlab.domain.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bme.projlab.domain.model.response.UsernameResponse
import com.bme.projlab.domain.modificationoptions.ModificationOptions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFirstViewModel @Inject constructor(
    private val modificationOptions: ModificationOptions
) : ViewModel(){
    var usernameResponse by mutableStateOf<UsernameResponse>(UsernameResponse.Loading)

    fun setUsername(username: String) = viewModelScope.launch{
        usernameResponse = UsernameResponse.Loading
        modificationOptions.modifyUsername(username).let { result ->
            usernameResponse = when(result){
                is UsernameResponse.Loading -> {
                    UsernameResponse.Loading
                }
                is UsernameResponse.Error -> {
                    UsernameResponse.Error
                }
                is UsernameResponse.Success -> {
                    UsernameResponse.Success
                }
            }
        }
    }

}