package com.challenge.model

import org.junit.Assert.assertEquals
import org.junit.Test

class PriceUnitTest {

    @Test
    fun priceToStringAppendsSymbolAndValue() {
        var price = Price("14.50")
        assertEquals("€14.50", price.toString())

        price = Price("12.30", Price.Currency.GBP)
        assertEquals("£12.30", price.toString())
    }

}