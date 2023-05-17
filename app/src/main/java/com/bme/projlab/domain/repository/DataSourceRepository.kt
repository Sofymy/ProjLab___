package com.bme.projlab.domain.repository

import com.bme.projlab.domain.model.element.*

interface DataSourceRepository {
    suspend fun getUser(): User
    suspend fun getTrip(id: Int): Trip?
    suspend fun getDestination(id: String): Destination?
    fun getTransferOptions(id: Int): TransferOptions?
    fun getAccommodation(id: Int): Accommodation?
    fun getTransferInfo(id: Int): TransferInfo?
}