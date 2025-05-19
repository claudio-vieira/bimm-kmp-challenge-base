package com.bimm.takehomeassignmnent.sakes.services

import com.bimm.takehomeassignmnent.sakes.data.SakeLocation
import com.bimm.takehomeassignmnent.utils.Variables
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

interface ISakeLocationService {
    suspend fun fetchSakeLocations(): List<SakeLocation>
}

class SakeLocationService(private val httpClient: HttpClient): ISakeLocationService {

    private val url = Variables.URL_API

    override suspend fun fetchSakeLocations(): List<SakeLocation> {
        return httpClient.get(url).body()
    }
}