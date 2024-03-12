package com.challenge.network.adapter

import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Custom Call implementation to handle our NetworkResponse types for retrofit
 */
class NetworkResponseCall<R>(
    private val delegate: Call<R>
) : Call<NetworkResponse<R>> {

    @Suppress("UNCHECKED_CAST")
    private fun Response<R>.toNetworkResponse() =
        Response.success(
            when (isSuccessful) {
                true -> NetworkResponse.Success(body() as R)
                false -> NetworkResponse.Error()
            }
        )

    override fun enqueue(callback: Callback<NetworkResponse<R>>) {
        delegate.enqueue(
            // intermediate callback implementation
            // passes both error & response to supplied callback.onResponse from retrofit
            // both error and success states are handled gracefully
            object : Callback<R> {
                override fun onFailure(call: Call<R>, t: Throwable) {
                    callback.onResponse(
                        this@NetworkResponseCall,
                        Response.success(NetworkResponse.Error())
                    )
                }

                override fun onResponse(call: Call<R>, response: Response<R>) {
                    callback.onResponse(
                        this@NetworkResponseCall,
                        response.toNetworkResponse()
                    )
                }
            }
        )
    }

    override fun execute(): Response<NetworkResponse<R>> {
        return delegate.execute().toNetworkResponse()
    }

    override fun isExecuted() = delegate.isExecuted

    override fun clone() = NetworkResponseCall(delegate.clone())

    override fun isCanceled() = delegate.isCanceled

    override fun cancel() = delegate.cancel()

    override fun request(): Request = delegate.request()

    override fun timeout(): Timeout = delegate.timeout()

}