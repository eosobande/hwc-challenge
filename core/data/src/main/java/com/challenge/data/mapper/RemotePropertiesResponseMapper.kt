package com.challenge.data.mapper

import android.text.Html
import com.challenge.model.Price
import com.challenge.model.Property
import com.challenge.model.PropertyList
import com.challenge.model.RatingBreakdown
import com.challenge.network.RemotePropertiesResponse

fun RemotePropertiesResponse.Property.toModel() =
    Property(
        name = name,
        overview = Html.fromHtml(overview, Html.FROM_HTML_MODE_LEGACY).toString(),
        overallRating = overallRating.overall,
        numberOfRatings = overallRating.numberOfRatings,
        lowestPricePerNight = lowestPricePerNight.toModel(),
        type = type,
        distance = "${distance.value} ${distance.units}",
        featured = isFeatured,
        images = imagesGallery.map { it.prefix + it.suffix },
        address = "$address1 $address2".trim(),
        ratingBreakdown = ratingBreakdown.toModel()
    )

fun RemotePropertiesResponse.Property.Price.toModel() = Price(value)

fun RemotePropertiesResponse.toModel() =
    PropertyList(
        properties = properties.map { it.toModel() },
        city = location.city.name
    )

fun RemotePropertiesResponse.Property.RatingBreakdown.toModel() =
    RatingBreakdown(
        ratingsCount = ratingsCount,
        security = security,
        location = location,
        staff = staff,
        entertaining = entertaining,
        clean = clean,
        facilities = facilities,
        value = value,
        average = average
    )
