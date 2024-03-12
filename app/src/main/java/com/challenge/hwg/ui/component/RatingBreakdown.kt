package com.challenge.hwg.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.challenge.hwg.R
import com.challenge.hwg.ui.theme.HWGCodeChallengeTheme
import com.challenge.model.RatingBreakdown
import com.challenge.hwg.extension.progress
import com.challenge.hwg.extension.rating

private val RatingsCategories = listOf(
    R.string.ratings_value_for_money,
    R.string.ratings_security,
    R.string.ratings_atmosphere,
    R.string.ratings_cleanliness,
    R.string.ratings_staff,
    R.string.location,
    R.string.ratings_facilities
)

@Composable
fun RatingBreakdown(
    ratingBreakdown: RatingBreakdown,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        RatingsCategories.forEachIndexed { i, id ->
            RatingBreakdownItem(
                title = stringResource(id = id),
                rating = when (id) {
                    R.string.ratings_security -> ratingBreakdown.security
                    R.string.ratings_atmosphere -> ratingBreakdown.entertaining
                    R.string.ratings_cleanliness -> ratingBreakdown.clean
                    R.string.ratings_staff -> ratingBreakdown.staff
                    R.string.location -> ratingBreakdown.location
                    R.string.ratings_facilities -> ratingBreakdown.facilities
                    else -> ratingBreakdown.value // default
                },
                modifier = Modifier.padding(top = if (i > 0) 5.dp else 0.dp)
            )
        }
    }
}

@Composable
private fun RatingBreakdownItem(
    title: String,
    rating: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.width(140.dp)
        )
        LinearProgressIndicator(
            progress = { rating.progress },
            modifier = Modifier
                .weight(1f)
                .height(10.dp)
                .clip(RoundedCornerShape(10.dp)),
            color = MaterialTheme.colorScheme.primary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant,
        )
        Text(
            text = rating.rating,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.End,
            modifier = Modifier.width(40.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRatingBreakdown() {
    HWGCodeChallengeTheme {
        RatingBreakdown(
            ratingBreakdown = RatingBreakdown(
                10, 10, 100, 10,
                10, 10, 10, 10, 10
            )
        )

    }
}