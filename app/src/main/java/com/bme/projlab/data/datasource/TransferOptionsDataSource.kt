package com.bme.projlab.data.datasource

import com.bme.projlab.domain.model.element.TransferOptions
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class TransferOptionsDataSource @Inject constructor(
    firebaseFirestore: FirebaseFirestore
) : DataSource<ArrayList<TransferOptions?>, Int, TransferOptions?> {

    override fun load(): ArrayList<TransferOptions?> {
        return arrayListOf(
            TransferOptions(1, null, null, null),
            TransferOptions(2, null, null, null)
        )
    }
    override fun get(id: Int): TransferOptions? {
        val options =  load()
        for (option: TransferOptions? in options) {
            if (option != null) {
                if(option.airportId == id){
                    return option
                }
            }
        }
        return null
    }
}