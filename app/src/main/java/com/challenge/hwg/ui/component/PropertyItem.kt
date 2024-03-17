package com.challenge.hwg.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.challenge.hwg.R
import com.challenge.hwg.extension.rating
import com.challenge.model.Property

@Composable
fun PropertyItem(
    property: Property,
    modifier: Modifier = Modifier,
    onPropertyClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(30.dp, 0.dp, 30.dp, 20.dp)
            .clickable(onClick = onPropertyClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .border(1.dp, Color.LightGray, RoundedCornerShape(20.dp))
                .padding(5.dp)
        ) {
            AsyncImage(
                model = property.images.firstOrNull(),
                contentDescription = stringResource(id = R.string.property_photo),
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 250.dp)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            ) {
                Text(
                    text = property.name,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                Rating(
                    rating = property.overallRating.rating,
                    numberOfRatings = property.numberOfRatings
                )
                Text(
                    text = stringResource(
                        id = R.string.distance,
                        property.type,
                        property.distance
                    ),
                    style = MaterialTheme.typography.bodyMedium
                )
                PriceFrom(
                    price = property.lowestPricePerNight,
                    alignEnd = true,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .align(Alignment.End)
                )
            }
        }
        if (property.featured) {
            FeaturedLabel(
                property.type,
                modifier = Modifier.padding(top = 20.dp)
            )
        }
    }
}