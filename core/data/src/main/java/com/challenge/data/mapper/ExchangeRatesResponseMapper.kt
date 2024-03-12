package com.challenge.data.mapper

import com.challenge.model.ExchangeRates
import com.challenge.network.ExchangeRateResponse

fun ExchangeRateResponse.toModel() = ExchangeRates(usd = rates.usd, gbp = rates.gbp)
