package com.bme.projlab.domain.model.element

data class TransferOptions(
    val airportId: Int,
    var road: TransferInfo?,
    var rail: TransferInfo?,
    var taxi: TransferInfo?
)
