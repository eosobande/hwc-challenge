package com.challenge.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ExchangeRatesUnitTest {

    @Test
    fun conversionIsCorrect() {
        val rates = ExchangeRates(1.092258, 0.852261)
        val price = Price("14.50")
        var converted = rates.convert(price, Price.Currency.EUR)
        assertEquals(price, converted)

        converted = rates.convert(price, Price.Currency.USD)
        assertNotEquals(price, converted)
        assertEquals(Price.Currency.USD, converted.currency)
        assertEquals("$15.84", converted.toString())

        converted = rates.convert(price, Price.Currency.GBP)
        assertNotEquals(price, converted)
        assertEquals(Price.Currency.GBP, converted.currency)
        assertEquals("£12.36", converted.toString())
    }

}