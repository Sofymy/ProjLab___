package com.bme.projlab.domain.model.element


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Flights(
    @SerializedName("data")
    var `data`: List<ReturnFlight>? = emptyList(),
    @SerializedName("message")
    var message: String? = "",
    @SerializedName("status")
    var status: Boolean? = false
) {
    data class ReturnFlight(
        @SerializedName("legs")
        var returnFlight: List<Flight>? = emptyList(),
        @SerializedName("price")
        var price: Price? = null,
        @SerializedName("score")
        var score: Double? = 0.0,
        @SerializedName("totalDuration")
        var totalDuration: Int? = 0
    ) {
        data class Flight(
            @SerializedName("arrival")
            var arrival: String? = "",
            @SerializedName("departure")
            var departure: String? = "",
            @SerializedName("destination")
            var destination: Destination? = null,
            @SerializedName("duration")
            var duration: Int? = 0,
            @SerializedName("id")
            var id: String?="",
            @SerializedName("stop_count")
            var stop_count: Int? = 0,
        )

        data class Price(
            @Expose
            var amount: Double? = 0.0,
            @Expose
            var last_updated: String? = "",
            @Expose
            var quote_age: Int? = 0,
            @Expose
            var score: Double? = 0.0,
            @Expose
            var transfer_type: String? = "",
            @Expose
            var update_status: String? = ""
        )
    }
}

data class Carrier(
    @Expose
    val alliance: Int,
    @Expose
    val alt_id: String,
    @Expose
    val display_code: String,
    @Expose
    val display_code_type: String,
    @Expose
    val id: Int,
    @Expose
    val name: String
)

data class Destination2(
    @Expose
    val alt_id: String,
    @Expose
    val display_code: String,
    @Expose
    val entity_id: Int,
    @Expose
    val id: Int,
    @Expose
    val name: String,
    @Expose
    val parent_entity_id: Int,
    @Expose
    val parent_id: Int,
    @Expose
    val type: String
)

data class Origin(
    @Expose
    val alt_id: String,
    @Expose
    val display_code: String,
    @Expose
    val entity_id: Int,
    @Expose
    val id: Int,
    @Expose
    val name: String,
    @Expose
    val parent_entity_id: Int,
    @Expose
    val parent_id: Int,
    @Expose
    val type: String
)

data class Stop(
    @Expose
    val alt_id: String,
    @Expose
    val display_code: String,
    @Expose
    val entity_id: Int,
    @Expose
    val id: Int,
    @Expose
    val name: String,
    @Expose
    val parent_entity_id: Int,
    @Expose
    val parent_id: Int,
    @Expose
    val type: String
)