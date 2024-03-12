package com.challenge.model

data class RatingBreakdown(
    val ratingsCount: Int,
    val security: Int,
    val location: Int,
    val staff: Int,
    val entertaining: Int,
    val clean: Int,
    val facilities: Int,
    val value: Int,
    val average: Int,
)
