package com.challenge.hwg.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.challenge.hwg.R
import com.challenge.hwg.ui.component.AppBar
import com.challenge.hwg.ui.component.PropertyItem
import com.challenge.hwg.ui.component.TestTags
import com.challenge.hwg.ui.theme.HWGCodeChallengeTheme
import com.challenge.model.Price
import com.challenge.model.Property
import com.challenge.model.RatingBreakdown

@Composable
fun PropertiesScreen(
    city: String,
    properties: List<Property>,
    onPropertyClick: (Int) -> Unit,
    refresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            AppBar(title = city, navigateUp = null)
        }
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            LazyColumn {
                item {
                    // list header with refresh button
                    Row(
                        modifier = Modifier.fillMaxSize().padding(30.dp, 20.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = stringResource(
                                id = R.string.showing_properties,
                                properties.size
                            ),
                            style = MaterialTheme.typography.titleMedium,
                            fontWeight = FontWeight.Bold
                        )
                        Icon(
                            imageVector = Icons.Outlined.Refresh,
                            contentDescription = stringResource(id = R.string.location),
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .size(32.dp)
                                .clickable(onClick = refresh)
                        )
                    }
                }
                itemsIndexed(
                    properties,
                    key = { _, it -> it.name }
                ) { index, it ->
                    PropertyItem(it) {
                        onPropertyClick(index)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPropertiesScreen() {
    HWGCodeChallengeTheme {
        PropertiesScreen(
            city = "Dublin",
            properties = List(5) {
                Property(
                    name = "Property ${it + 1}",
                    overview = "A warm welcome awaits you at Property ${it + 1}.",
                    overallRating = 89,
                    numberOfRatings = "1156",
                    lowestPricePerNight = Price("77.85"),
                    type = "Hotel",
                    distance = "1.6km",
                    featured = it < 2,
                    images = emptyList(),
                    address = "29 Bachelors Walk, Dublin 1",
                    ratingBreakdown = RatingBreakdown(
                        10, 10, 10, 10,
                        10, 10, 10, 10, 10,
                    )
                )
            },
            onPropertyClick = {},
            refresh = {}
        )
    }
}