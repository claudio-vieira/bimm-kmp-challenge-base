package com.bimm.takehomeassignmnent.presentation

import com.bimm.takehomeassignmnent.sakes.data.SakeLocation

data class SakeLocationState (
    val sakeLocations: List<SakeLocation> = listOf(),
    val loading: Boolean = false,
    val error: String? = null
)
