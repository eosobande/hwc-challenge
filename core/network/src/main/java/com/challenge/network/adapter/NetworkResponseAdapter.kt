package com.challenge.network.adapter

import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class NetworkResponseAdapter<R>(
    private val responseType: Type
) : CallAdapter<R, Call<NetworkResponse<R>>> {

    override fun responseType(): Type = responseType

    override fun adapt(call: Call<R>): Call<NetworkResponse<R>> = NetworkResponseCall(call)

    class Factory : CallAdapter.Factory() {

        override fun get(
            returnType: Type,
            annotations: Array<out Annotation>,
            retrofit: Retrofit
        ): CallAdapter<*, *>? {
            // Return type must be parameterized as Call<NetworkResponse<R>>
            if (returnType !is ParameterizedType) return null
            // check if the return type is a retrofit Call type
            // return type will be wrapped in a Call type if we use suspend functions
            if (returnType.rawType != Call::class.java) return null
            // get parameter upper bound NetworkResponse
            val responseType = returnType.actualTypeArguments[0]
            if (responseType !is ParameterizedType) return null
            // Response must be parameterized as NetworkResponse<T>
            if (responseType.rawType != NetworkResponse::class.java) return null
            // get parameter upper bound <T>
            val successBodyType = responseType.actualTypeArguments[0]
            return NetworkResponseAdapter<Any>(successBodyType)
        }

    }

}