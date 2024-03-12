package com.challenge.hwg.presentation

import com.challenge.model.Price

sealed interface PropertyDetailsState {

    /**
     * All states here will have a price available
     */
    val price: Price

    /**
     * During the conversion request, price in current currency is still displayed
     */
    data class Converting(override val price: Price) : PropertyDetailsState

    /**
     * Conversion error state with the price displayed before attempted conversion
     */
    data class Error(
        val message: String,
        override val price: Price
    ) : PropertyDetailsState

    /**
     * Either default EUR price or successfully converted price
     */
    data class Loaded(override val price: Price) : PropertyDetailsState

}