package com.bme.projlab.data.repository

import com.bme.projlab.data.datasource.*
import com.bme.projlab.domain.model.element.*
import com.bme.projlab.domain.repository.DataSourceRepository

class DataSourceRepositoryImpl(
    private val airportDataSource: AirportDataSource,
    private val accommodationDataSource: AccommodationDataSource,
    private val destinationDataSource: DestinationDataSource,
    private val flightDataSource: FlightDataSource,
    private val transferInfoDataSource: TransferInfoDataSource,
    private val transferOptionsDataSource: TransferOptionsDataSource,
    private val tripDataSource: TripDataSource,
    private val userDataSource: UserDataSource
    ): DataSourceRepository {

    override suspend fun getUser(): User {
        val user = userDataSource.loadUser()
        val heartedTrips = arrayListOf(getTrip(1), getTrip(2))
        val visitedLocations = arrayListOf("Paris", "Nice")
        val receivedTrips = arrayListOf(tripDataSource.get(0))

        user.heartedTrips = heartedTrips
        user.visitedLocations = visitedLocations
        user.receivedTrips = receivedTrips

        return user
    }

    override suspend fun getTrip(id: Int): Trip? {
        val trip = tripDataSource.get(id)
        val toDestination = destinationDataSource.get("Nice")
        val fromDestination = destinationDataSource.get("Naples")
        val accommodation = accommodationDataSource.get(1)

        if (trip != null) {
            if (toDestination != null) {
                trip.toDestination = toDestination
            }
            if (fromDestination != null) {
                trip.fromDestination = fromDestination
            }
            trip.accommodation = accommodation
        }

        return trip
    }

    override suspend fun getDestination(id: String): Destination? {
        return destinationDataSource.get(id)
    }

    fun getAllDestination(): ArrayList<Destination> {
        return destinationDataSource.getAll()
    }

    override  fun getTransferOptions(id: Int): TransferOptions? {
        val transferOptions = transferOptionsDataSource.get(id)
        val road = transferInfoDataSource.get(1)
        val rail = transferInfoDataSource.get(1)
        val taxi = transferInfoDataSource.get(0)
        if (transferOptions != null) {
            transferOptions.rail = rail
            transferOptions.road = road
            transferOptions.taxi = taxi
        }
        return transferOptions
    }

    override fun getAccommodation(id: Int): Accommodation? {
        return accommodationDataSource.get(id)
    }

    override fun getTransferInfo(id: Int): TransferInfo? {
        return transferInfoDataSource.get(id)
    }
}