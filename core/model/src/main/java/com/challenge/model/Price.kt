package com.challenge.model

data class Price(
    val value: String,
    val currency: Currency = Currency.EUR
) {

    override fun toString() = "${currency.symbol}$value"

    enum class Currency(val symbol: Char) {
        EUR('€'),
        USD('$'),
        GBP('£')
    }

}