package com.bimm.takehomeassignmnent.sakes.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SakeLocation(
    val name: String,
    val description: String,
    val picture: String? = null,
    val rating: Float,
    val address: String,
    val coordinates: List<Double>,
    @SerialName("google_maps_link")
    val googleMapsLink: String,
    val website: String
)