package com.bme.projlab.domain.model.element

import androidx.compose.ui.graphics.vector.ImageVector
import com.google.firebase.firestore.PropertyName

data class Destination(
    @get: PropertyName("name") @set: PropertyName("name") var name: String? ="",
    @get: PropertyName("destinationTraits") @set: PropertyName("destinationTraits") var destinationTraits: DestinationTraits? = null,
    @get: PropertyName("picture") @set: PropertyName("picture") var picture: ImageVector? = null
){
    constructor() : this("",null,null)
}