package com.bme.projlab.data.datasource

import com.bme.projlab.domain.model.element.Accommodation
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject


//Todo: delete this file

class AccommodationDataSource @Inject constructor(
    firebaseFirestore: FirebaseFirestore
) : DataSource<ArrayList<Accommodation>, Int, Accommodation?> {

    override fun load(): ArrayList<Accommodation> {
        return arrayListOf(
            Accommodation(1,"Hotel Projlab", 123),
            Accommodation(2, "Hotel Naples", 324)
        )
    }

    override fun get(id: Int): Accommodation? {
        val accommodations =  load()
        for (accommodation: Accommodation in accommodations) {
            if(accommodation.id == id){
                return accommodation
            }
        }
        return null
    }
}