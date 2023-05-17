package com.bme.projlab.domain.repository

import com.bme.projlab.domain.model.element.Destination
import com.bme.projlab.domain.model.element.DestinationTraits

interface DestinationRepository {

    suspend fun loadDestinations(): ArrayList<DestinationTraits>
}
