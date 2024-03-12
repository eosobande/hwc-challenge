package com.challenge.data.datasource

import com.challenge.model.PropertyList
import com.challenge.model.Result
import io.reactivex.rxjava3.core.Observable


interface PropertyDataSource {

    suspend fun getProperties(): Observable<Result<PropertyList>>

}