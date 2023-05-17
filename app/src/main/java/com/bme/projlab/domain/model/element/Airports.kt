package com.bme.projlab.domain.model.element



import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Airports(
    @SerializedName("data")
    val data: List<Airport>,
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: Boolean,
    @SerializedName("timestamp")
    val timestamp: Long
) {
    data class Airport(
        @SerializedName("AirportInformation")
        val airportInformation: AirportInformation,
        @SerializedName("CityId")
        val cityId: String,
        @SerializedName("CityName")
        val cityName: String,
        @SerializedName("CityNameEn")
        val cityNameEn: String,
        @SerializedName("CountryId")
        val countryId: String,
        @SerializedName("CountryName")
        val countryName: String,
        @SerializedName("GeoContainerId")
        val geoContainerId: String,
        @SerializedName("GeoId")
        val geoId: String,
        @SerializedName("IataCode")
        val iataCode: String,
        @SerializedName("LocalizedPlaceName")
        val localizedPlaceName: String,
        @SerializedName("Location")
        val location: String,
        @SerializedName("PlaceId")
        val placeId: String? = "",
        @SerializedName("PlaceName")
        val placeName: String,
        @SerializedName("PlaceNameEn")
        val placeNameEn: String,
        @SerializedName("ResultingPhrase")
        var resultingPhrase: String,
        @SerializedName("Tags")
        val tags: List<String>,
        @SerializedName("UntransliteratedResultingPhrase")
        val untransliteratedResultingPhrase: String
    ) {
        data class AirportInformation(
            @SerializedName("CityId")
            val cityId: String,
            @SerializedName("CityName")
            val cityName: String,
            @SerializedName("CityNameEn")
            val cityNameEn: String,
            @SerializedName("CountryId")
            val countryId: String,
            @SerializedName("CountryName")
            val countryName: String,
            @SerializedName("Distance")
            val distance: Distance,
            @SerializedName("GeoContainerId")
            val geoContainerId: String,
            @SerializedName("GeoId")
            val geoId: String,
            @SerializedName("Highlighting")
            val highlighting: Any,
            @SerializedName("IataCode")
            val iataCode: String,
            @SerializedName("LocalizedPlaceName")
            val localizedPlaceName: String,
            @SerializedName("Location")
            val location: String,
            @SerializedName("PlaceId")
            val placeId: String,
            @SerializedName("PlaceName")
            val placeName: String,
            @SerializedName("PlaceNameEn")
            val placeNameEn: String,
            @SerializedName("RegionId")
            val regionId: String,
            @SerializedName("ResultingPhrase")
            val resultingPhrase: Any
        ) {
            data class Distance(
                @SerializedName("UnitCode")
                val unitCode: String,
                @SerializedName("Value")
                val value: Double
            )
        }
    }
}