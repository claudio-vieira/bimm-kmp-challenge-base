package com.bimm.takehomeassignmnent.sakes.data

import com.bimm.takehomeassignmnent.ResponseState
import com.bimm.takehomeassignmnent.sakes.services.ISakeLocationService

interface ISakeRepository {
    suspend fun fetchSakeLocations(): ResponseState<List<SakeLocation>>
}

open class SakeRepository(
    private val service: ISakeLocationService
): ISakeRepository {
    override suspend fun fetchSakeLocations(): ResponseState<List<SakeLocation>> {
        val fetchedArticles = service.fetchSakeLocations()
        return ResponseState.Success(fetchedArticles)
    }
}