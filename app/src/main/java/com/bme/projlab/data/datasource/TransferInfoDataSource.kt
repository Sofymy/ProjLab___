package com.bme.projlab.data.datasource

import com.bme.projlab.domain.model.element.TransferInfo
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

class TransferInfoDataSource @Inject constructor(
    firebaseFirestore: FirebaseFirestore
) : DataSource<ArrayList<TransferInfo>, Int, TransferInfo?> {

    override fun load(): ArrayList<TransferInfo> {
        return arrayListOf(
            TransferInfo(1, true),
            TransferInfo(2, false)
        )
    }
    override fun get(id: Int): TransferInfo? {
        val infos =  load()
        for (info: TransferInfo in infos) {
            if(info.id == id){
                return info
            }
        }
        return null
    }
}