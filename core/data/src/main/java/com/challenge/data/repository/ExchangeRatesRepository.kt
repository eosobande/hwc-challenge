package com.challenge.data.repository

import com.challenge.model.ExchangeRates
import com.challenge.model.Result
import io.reactivex.rxjava3.core.Observable

interface ExchangeRatesRepository {

    suspend fun rates(): Observable<Result<ExchangeRates>>

}
