package com.bme.projlab.domain.repository

import com.bme.projlab.domain.model.element.DestinationTraits

interface SearchDestinationRepository {
    fun searchByAttributes(destinations: ArrayList<DestinationTraits>, capital: Boolean, historical: Boolean, warm: Boolean, first: Boolean): ArrayList<DestinationTraits>
}