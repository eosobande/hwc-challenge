package com.challenge.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ExchangeRateResponse(val rates: Rates) {

    @Serializable
    data class Rates(
        @SerialName("USD") val usd: Double,
        @SerialName("GBP") val gbp: Double
    )

}