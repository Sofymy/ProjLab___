package com.bme.projlab.domain.model.element

import androidx.compose.ui.graphics.vector.ImageVector
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.PropertyName

data class DestinationTraits(
    @DocumentId
    var name: String,
    @get: PropertyName("warm") @set: PropertyName("warm") var warm: Boolean? = false,
    @get: PropertyName("historical") @set: PropertyName("historical") var historical: Boolean? = false,
    @get: PropertyName("capital") @set: PropertyName("capital") var capital: Boolean? = false
){
    constructor() : this("",false, false, false)
}