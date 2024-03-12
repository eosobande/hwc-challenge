package com.challenge.data.datasource

import com.challenge.model.ExchangeRates
import com.challenge.model.Result
import io.reactivex.rxjava3.core.Observable


interface ExchangeRatesDataSource {

    suspend fun rates(): Observable<Result<ExchangeRates>>

}