package com.challenge.data.repository

import com.challenge.model.PropertyList
import com.challenge.model.Result
import io.reactivex.rxjava3.core.Observable

interface PropertyRepository {

    suspend fun getProperties(): Observable<Result<PropertyList>>

}
