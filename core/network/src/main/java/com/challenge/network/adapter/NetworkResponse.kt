package com.challenge.network.adapter

sealed interface NetworkResponse<R> {

    data class Error<R>(val message: String = "OOPS! Something went wrong") : NetworkResponse<R>

    data class Success<R>(val data: R) : NetworkResponse<R>

}