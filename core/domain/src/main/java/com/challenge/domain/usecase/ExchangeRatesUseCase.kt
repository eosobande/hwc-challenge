package com.challenge.domain.usecase

import com.challenge.data.repository.ExchangeRatesRepository
import javax.inject.Inject

class ExchangeRatesUseCase @Inject constructor(
    private val exchangeRatesRepository: ExchangeRatesRepository
) {
    suspend operator fun invoke() = exchangeRatesRepository.rates()
}