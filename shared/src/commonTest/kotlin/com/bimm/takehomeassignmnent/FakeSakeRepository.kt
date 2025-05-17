package com.bimm.takehomeassignmnent

import com.bimm.takehomeassignmnent.sakes.data.SakeDataSource
import com.bimm.takehomeassignmnent.sakes.data.SakeLocation

class FakeSakeDataSource(
    private val fakeData: List<SakeLocation> = emptyList()
) : SakeDataSource() {
    override suspend fun getAllSakesJSON(): List<SakeLocation> {
        return fakeData
    }
}