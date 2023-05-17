package com.bme.projlab.domain.model.response

sealed class TripResponse<out T> {
    class Loading<out T>: TripResponse<T>()

    data class Success<out T>(
        val data: T
    ): TripResponse<T>()

    data class Failure<out T>(
        val errorMessage: String
    ): TripResponse<T>()
}