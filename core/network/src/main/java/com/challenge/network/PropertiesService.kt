package com.challenge.network

import com.challenge.network.adapter.NetworkResponse
import retrofit2.http.GET

interface PropertiesService {

    @GET("a1517b9da90dd6877385a65f324ffbc3/raw/058e83aa802907cb0032a15ca9054da563138541/properties.json")
    suspend fun listProperties(): NetworkResponse<RemotePropertiesResponse>

}