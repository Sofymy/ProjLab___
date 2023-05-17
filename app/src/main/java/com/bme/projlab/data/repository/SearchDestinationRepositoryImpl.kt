package com.bme.projlab.data.repository

import com.bme.projlab.domain.model.element.DestinationTraits
import com.bme.projlab.domain.repository.SearchDestinationRepository
import com.bme.projlab.domain.repository.UserRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class SearchDestinationRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
): SearchDestinationRepository {

    override fun searchByAttributes(
        destinations: ArrayList<DestinationTraits>,
        capital: Boolean,
        historical: Boolean,
        warm: Boolean,
        first: Boolean
    ): ArrayList<DestinationTraits> {
        val cities = ArrayList<DestinationTraits>()
        for(destination in destinations){
            if((capital && destination.capital == true) || !capital){
                if(historical && destination.historical == true|| !historical){
                    if(warm == destination.warm){
                        cities.add(destination)
                    }
                }
            }
        }
        return cities
    }


}