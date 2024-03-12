package com.challenge.data

import com.challenge.data.datasource.ExchangeRatesDataSource
import com.challenge.data.datasource.PropertyDataSource
import com.challenge.data.datasource.RemoteExchangeRatesDataSource
import com.challenge.data.datasource.RemotePropertyDataSource
import com.challenge.data.repository.ExchangeRatesRepository
import com.challenge.data.repository.ExchangeRatesRepositoryImpl
import com.challenge.data.repository.PropertyRepository
import com.challenge.data.repository.PropertyRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsPropertyDataSource(
        source: RemotePropertyDataSource
    ): PropertyDataSource

    @Binds
    fun bindsPropertyRepository(
        source: PropertyRepositoryImpl
    ): PropertyRepository

    @Binds
    fun bindsExchangeRatesDataSource(
        source: RemoteExchangeRatesDataSource
    ): ExchangeRatesDataSource

    @Binds
    fun bindsExchangeRatesRepository(
        source: ExchangeRatesRepositoryImpl
    ): ExchangeRatesRepository

}