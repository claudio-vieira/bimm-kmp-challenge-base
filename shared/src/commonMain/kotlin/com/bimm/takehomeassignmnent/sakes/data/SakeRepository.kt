package com.bimm.takehomeassignmnent.sakes.data

class SakeRepository(
    private val dataSource: SakeDataSource
) {
    suspend fun getAllSakesJSON() = dataSource.getAllSakesJSON()
}