package com.bme.projlab.domain.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.ui.text.capitalize
import com.bme.projlab.data.repository.DestinationRepositoryImpl
import com.bme.projlab.data.repository.SearchDestinationRepositoryImpl
import com.bme.projlab.data.repository.UserRepositoryImpl
import com.bme.projlab.domain.model.element.DestinationTraits
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class SearchDestinationPreferencesViewModel @Inject constructor(
    private val searchDestinationRepositoryImpl: SearchDestinationRepositoryImpl,
    private val destinationRepositoryImpl: DestinationRepositoryImpl,
    private val userRepositoryImpl: UserRepositoryImpl
) : ViewModel(){

    suspend fun searchByAttributes(
        stateCapital: Boolean,
        stateHistorical: Boolean,
        warm: Boolean,
        first: Boolean
    ): ArrayList<DestinationTraits> {
        val destinations = destinationRepositoryImpl.loadDestinations()
        if(first){
            val visitedLocations = userRepositoryImpl.loadVisitedLocations()
            val iterator = destinations.iterator()
            while(iterator.hasNext()){
                val item = iterator.next()
                if (visitedLocations.contains(item.name.replaceFirstChar {
                        if (it.isLowerCase()) it.titlecase(
                            Locale.ROOT
                        ) else it.toString()
                    })){
                    iterator.remove()
                }
            }
        }
        return replaceUpper(searchDestinationRepositoryImpl.searchByAttributes(destinations,
            stateCapital,
            stateHistorical,
            warm,
            first)
        )
    }

    private fun replaceUpper(dest: ArrayList<DestinationTraits>): ArrayList<DestinationTraits>{
        for(d in dest){
            d.name = d.name.capitalize(Locale.ROOT)
        }
        return dest
    }

}