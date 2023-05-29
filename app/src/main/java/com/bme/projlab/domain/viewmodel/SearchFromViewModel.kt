package com.bme.projlab.domain.viewmodel

import android.location.Address
import android.location.Geocoder
import android.util.Log
import androidx.compose.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bme.projlab.data.repository.DestinationRepositoryImpl
import com.bme.projlab.domain.model.element.DestinationTraits
import com.google.android.gms.common.util.CollectionUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class SearchFromViewModel @Inject constructor(
    private val destinationRepositoryImpl: DestinationRepositoryImpl,
) : ViewModel() {
    private var _list = MutableLiveData<List<DestinationTraits>>()
    val list: LiveData<List<DestinationTraits>>
        get() = _list


    suspend fun loadDestinations() {
        _list.postValue(destinationListData())
    }

    suspend fun performQuery(query: String) {
        val filteredList = destinationListData()
            .filter {
                it.name.lowercase(Locale.getDefault()).startsWith(
                    query.lowercase(Locale.getDefault())
                )
            }
        _list.value = filteredList
    }

    private suspend fun destinationListData(): ArrayList<DestinationTraits> {
        val lowercase = destinationRepositoryImpl.loadDestinations()
        for(city in lowercase){
            city.name = city.name.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }
        return lowercase
    }

}
