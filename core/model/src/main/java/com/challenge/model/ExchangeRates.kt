package com.challenge.model

import java.math.BigDecimal
import java.math.RoundingMode

data class ExchangeRates(val usd: Double, val gbp: Double) {
    /**
     * @return new [Price] converted from [price.currency] to [to] currency
     */
    fun convert(price: Price, to: Price.Currency): Price {

        // cannot convert to EUR, return same price
        if (price.currency != Price.Currency.EUR) return price

        // used BigDecimal for arithmetic to prevent loss of precision with double values
        val rate = BigDecimal(
            when (to) {
                Price.Currency.EUR -> return price // unreachable, added to satisfy when() must be exhaustive
                Price.Currency.GBP -> gbp
                Price.Currency.USD -> usd
            }
        )

        val value = rate.times(BigDecimal(price.value))
            // convert to 2 decimal places and round towards nearest neighbor
            .setScale(2, RoundingMode.HALF_UP)
            .toPlainString()

        // new price in to currency
        return Price(value, to)

    }
}
