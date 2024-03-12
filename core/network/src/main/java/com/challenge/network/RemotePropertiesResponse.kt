package com.challenge.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RemotePropertiesResponse(
    val properties: List<Property>,
    val location: Location
) {

    @Serializable
    data class Property(
        val name: String,
        val overview: String,
        val overallRating: Rating,
        val lowestPricePerNight: Price,
        val type: String,
        val distance: Distance,
        val isFeatured: Boolean,
        val imagesGallery: List<GalleryImage>,
        val address1: String,
        val address2: String,
        val ratingBreakdown: RatingBreakdown
    ) {

        @Serializable
        data class Rating(val overall: Int, val numberOfRatings: String)

        @Serializable
        data class Price(val value: String, val currency: String)

        @Serializable
        data class Distance(val value: Double, val units: String)

        @Serializable
        data class GalleryImage(val prefix: String, val suffix: String)

        @Serializable
        data class RatingBreakdown(
            val ratingsCount: Int,
            val security: Int,
            val location: Int,
            val staff: Int,
            @SerialName("fun") val entertaining: Int,
            val clean: Int,
            val facilities: Int,
            val value: Int,
            val average: Int,
        )
    }

    @Serializable
    data class Location(val city: City) {

        @Serializable
        data class City(val name: String, val country: String)

    }

}
