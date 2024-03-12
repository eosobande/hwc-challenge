package com.challenge.network

import android.app.Application
import com.challenge.network.adapter.NetworkResponseAdapter
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Cache
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun providesJson(): Json {
        return Json { ignoreUnknownKeys = true }
    }

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class GitHubRetrofit

    @Qualifier
    @Retention(AnnotationRetention.RUNTIME)
    annotation class ExchangeRatesRetrofit

    @Provides
    @Singleton
    fun providesOkHttpClient(app: Application): OkHttpClient {
        return OkHttpClient.Builder()
            .retryOnConnectionFailure(false)
            .readTimeout(30, TimeUnit.SECONDS)
            .cache(Cache(app.cacheDir, 1 * 1024 * 1024))
            .build()
    }

    @Provides
    @Singleton
    fun providesNetworkCallAdapterFactory(): CallAdapter.Factory {
        return NetworkResponseAdapter.Factory()
    }

    @Provides
    @Singleton
    @GitHubRetrofit
    fun provideGitRetrofit(
        json: Json,
        client: OkHttpClient,
        networkCallAdapterFactory: CallAdapter.Factory
    ) = Retrofit.Builder()
        .baseUrl("https://gist.githubusercontent.com/PedroTrabulo-Hostelworld/")
        .client(client)
        .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
        .addCallAdapterFactory(networkCallAdapterFactory)
        .build()

    @Provides
    @Singleton
    @ExchangeRatesRetrofit
    fun provideRatesRetrofit(
        json: Json,
        okHttpClient: OkHttpClient,
        networkCallAdapterFactory: CallAdapter.Factory
    ) = Retrofit.Builder()
        .baseUrl("http://api.exchangeratesapi.io/v1/")
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory(MediaType.get("application/json")))
        .addCallAdapterFactory(networkCallAdapterFactory)
        .build()

    @Provides
    @Singleton
    fun providePropertiesService(@GitHubRetrofit retrofit: Retrofit) =
        retrofit.create(PropertiesService::class.java)

    @Provides
    @Singleton
    fun provideExchangeRatesService(@ExchangeRatesRetrofit retrofit: Retrofit) =
        retrofit.create(ExchangeRatesService::class.java)

}

