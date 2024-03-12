package com.challenge.data.datasource

import com.challenge.data.mapper.toModel
import com.challenge.model.PropertyList
import com.challenge.model.Result
import com.challenge.network.PropertiesService
import com.challenge.network.adapter.NetworkResponse
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RemotePropertyDataSource @Inject constructor(
    private val propertiesService: PropertiesService
) : PropertyDataSource {

    override suspend fun getProperties(): Observable<Result<PropertyList>> {
        return Observable.just(
            when(val response = propertiesService.listProperties()) {
                is NetworkResponse.Error -> Result.Failure(response.message)
                is NetworkResponse.Success -> Result.Success(response.data.toModel())
            }
        )
    }

}