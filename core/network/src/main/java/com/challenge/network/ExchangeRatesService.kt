package com.challenge.network

import com.challenge.network.adapter.NetworkResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ExchangeRatesService {

    @GET("{date}")
    suspend fun convert(
        @Path("date") date: String,
        @Query("access_key") accessKey: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String
    ): NetworkResponse<ExchangeRateResponse>

}