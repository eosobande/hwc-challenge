package com.challenge.data.datasource

import com.challenge.data.BuildConfig
import com.challenge.data.mapper.toModel
import com.challenge.model.ExchangeRates
import com.challenge.model.Result
import com.challenge.network.ExchangeRatesService
import com.challenge.network.adapter.NetworkResponse
import io.reactivex.rxjava3.core.Observable
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

class RemoteExchangeRatesDataSource @Inject constructor(
    private val exchangeRatesService: ExchangeRatesService
) : ExchangeRatesDataSource {

    override suspend fun rates(): Observable<Result<ExchangeRates>> {
        // get current date as yyyy-MM-dd format
        val formatter = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val date = formatter.format(Calendar.getInstance().time)


        val response = exchangeRatesService.convert(
            date = date,
            accessKey = BuildConfig.EXCHANGE_RATES_ACCESS_KEY,
            base = "EUR",
            symbols = "GBP,USD"
        )

        return Observable.just(
            when (response) {
                is NetworkResponse.Error -> Result.Failure(response.message)
                is NetworkResponse.Success -> Result.Success(response.data.toModel())
            }
        )
    }

}