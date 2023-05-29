package com.bme.projlab.domain.model.element

import com.google.firebase.firestore.PropertyName

data class ReceivedTrip(
    var trip: Int? = 0,
    var username: String? = "",
){
    constructor() : this(0,null)
}