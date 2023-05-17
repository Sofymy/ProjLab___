package com.bme.projlab.domain.model.element

import com.bme.projlab.domain.model.element.Badge
import com.bme.projlab.domain.model.element.Trip

data class User(
    val id: Int,
    var heartedTrips: ArrayList<Trip?>?,
    var visitedLocations: ArrayList<String>?,
    val badges: ArrayList<Badge>?,
    var receivedTrips: ArrayList<Trip?>?,
    //val notificationOptions: null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (heartedTrips != other.heartedTrips) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + heartedTrips.hashCode()
        return result
    }


}
