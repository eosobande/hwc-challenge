package com.challenge.data.repository

import com.challenge.data.datasource.PropertyDataSource
import com.challenge.model.PropertyList
import com.challenge.model.Result
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class PropertyRepositoryImpl @Inject constructor(
    private val propertyDataSource: PropertyDataSource
): PropertyRepository {

    override suspend fun getProperties(): Observable<Result<PropertyList>> {
        return propertyDataSource.getProperties()
    }

}