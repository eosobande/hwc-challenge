package com.challenge.model


sealed interface Result<S> {

    data class Success<S>(val data: S) : Result<S>

    data class Failure<S>(val message: String) : Result<S>

}
