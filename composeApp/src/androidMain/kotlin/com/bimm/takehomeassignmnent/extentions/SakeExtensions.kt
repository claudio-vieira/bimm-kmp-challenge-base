package com.bimm.takehomeassignmnent.extentions

import com.bimm.takehomeassignmnent.sakes.data.SakeLocation

fun SakeLocation.formattedRating(): String {
    return "⭐ $rating"
}

fun SakeLocation.isValidImageUrl(): Boolean {
    if (picture.isNullOrBlank()) return false

    // Check if it looks like an image (basic filter by file extension)
    val imageExtensions = listOf(".jpg", ".jpeg", ".png", ".webp", ".gif", ".bmp", ".svg")
    return imageExtensions.any { picture!!.lowercase().contains(it) } && picture!!.startsWith("http")
}