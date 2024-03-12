package com.challenge.model

data class Property(
    val name: String,
    val overview: String,
    val overallRating: Int,
    val numberOfRatings: String,
    val lowestPricePerNight: Price,
    val type: String,
    val distance: String,
    val featured: Boolean,
    val images: List<String>,
    val address: String,
    val ratingBreakdown: RatingBreakdown
)
