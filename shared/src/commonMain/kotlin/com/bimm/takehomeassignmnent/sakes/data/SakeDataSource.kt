package com.bimm.takehomeassignmnent.sakes.data

import com.bimm.takehomeassignmnent.utils.jsonSakeLocationString
import kotlinx.serialization.json.Json

open class SakeDataSource {

    private val json = Json {
        ignoreUnknownKeys = true
        coerceInputValues = true  // Handle nulls with default values
    }

    //This would be a call to the database or API using "suspend" (from API would be SakeService interface)
    //but instead it's provided as string
    open suspend fun getAllSakesJSON(): List<SakeLocation> {
        val jsonString = jsonSakeLocationString
        val list = loadLocationsFromJson(jsonString)
        return list
    }

    private fun loadLocationsFromJson(jsonString: String): List<SakeLocation> {
        return json.decodeFromString<List<SakeLocation>>(jsonString)
    }
}