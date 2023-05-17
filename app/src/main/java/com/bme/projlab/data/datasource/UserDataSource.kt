package com.bme.projlab.data.datasource

import com.bme.projlab.domain.model.element.*
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

//Todo: delete this file
class UserDataSource @Inject constructor(
    firebaseFirestore: FirebaseFirestore
){

    fun loadUser(): User {
        return User(1,
            null,
            null,
            null,
            null
        )
    }

}