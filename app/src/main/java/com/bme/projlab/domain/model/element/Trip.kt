package com.bme.projlab.domain.model.element
import com.bme.projlab.domain.model.element.Flights.ReturnFlight

data class Trip(
    var tripId: Int? = (1000000..9999999).random(),
    var flight: ReturnFlight? = null,
    var toDestination: Destination? = null,
    var fromDestination: Destination? = null,
    var accommodation: Accommodation? = null
){
    constructor() : this(0,null,null,null,null)
}
