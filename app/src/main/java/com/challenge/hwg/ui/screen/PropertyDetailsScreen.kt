package com.challenge.hwg.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.challenge.hwg.R
import com.challenge.hwg.extension.rating
import com.challenge.hwg.presentation.PropertyDetailsModel
import com.challenge.hwg.presentation.PropertyDetailsState
import com.challenge.hwg.ui.component.AppBar
import com.challenge.hwg.ui.component.ImageGallery
import com.challenge.hwg.ui.component.PropertyDetailsBottomBar
import com.challenge.hwg.ui.component.Rating
import com.challenge.hwg.ui.component.RatingBreakdown
import com.challenge.hwg.ui.component.Toast
import com.challenge.hwg.ui.theme.HWGCodeChallengeTheme
import com.challenge.model.Price
import com.challenge.model.Property
import com.challenge.model.RatingBreakdown

@Composable
fun PropertyDetailsScreen(
    city: String,
    property: Property,
    back: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PropertyDetailsModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.subscribeAsState(
        initial = PropertyDetailsState.Loaded(property.lowestPricePerNight)
    )

    Scaffold(
        topBar = {
            AppBar(title = property.name, navigateUp = back)
        },
        bottomBar = {
            PropertyDetailsBottomBar(uiState) {
                viewModel.convert(property.lowestPricePerNight, it)
            }
        }
    ) { innerPadding ->

        (uiState as? PropertyDetailsState.Error)?.let { (message) ->
            // Toast error message
            Toast(text = message)
        }

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
        ) {
            ImageGallery(
                images = property.images,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            )
            Column(modifier = Modifier.padding(30.dp, 20.dp)) {
                Text(text = property.type)
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = property.name,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.weight(1f)
                    )
                    Rating(
                        rating = property.overallRating.rating,
                        numberOfRatings = null
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.LocationOn,
                            contentDescription = stringResource(id = R.string.location),
                        )
                        Text(
                            text = city,
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(start = 5.dp)
                        )
                    }
                    Text(
                        text = stringResource(
                            id = R.string.ratings_count,
                            property.ratingBreakdown.ratingsCount
                        ),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(id = R.string.about),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = property.overview,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 4,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(id = R.string.ratings),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
                Rating(
                    rating = property.overallRating.rating,
                    numberOfRatings = stringResource(
                        id = R.string.ratings_count,
                        property.ratingBreakdown.ratingsCount
                    ),
                    mediumSizeRatingsText = true
                )
                Spacer(modifier = Modifier.height(20.dp))
                RatingBreakdown(ratingBreakdown = property.ratingBreakdown)
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(id = R.string.location),
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.LocationOn,
                        contentDescription = stringResource(R.string.location),
                    )
                    Text(
                        text = property.address,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = 5.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPropertyDetailsScreen() {
    HWGCodeChallengeTheme {
        PropertyDetailsScreen(
            city = "Dublin",
            property = Property(
                name = "St Christopher's Barcelona",
                overview = "A warm welcome awaits you at Property 1.",
                overallRating = 89,
                numberOfRatings = "1156",
                lowestPricePerNight = Price("77.85"),
                type = "Hotel",
                distance = "1.6km",
                featured = true,
                images = emptyList(),
                address = "29 Bachelors Walk, Dublin 1",
                ratingBreakdown = RatingBreakdown(
                    10, 10, 10, 10,
                    10, 10, 10, 10, 10,
                )
            ),
            back = {}
        )
    }
}