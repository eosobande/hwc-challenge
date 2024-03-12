package com.challenge.data.repository

import com.challenge.data.datasource.ExchangeRatesDataSource
import com.challenge.model.ExchangeRates
import com.challenge.model.Result
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class ExchangeRatesRepositoryImpl @Inject constructor(
    private val exchangeRatesDataSource: ExchangeRatesDataSource
): ExchangeRatesRepository {

    override suspend fun rates(): Observable<Result<ExchangeRates>> {
        return exchangeRatesDataSource.rates()
    }

}