package com.challenge.hwg.ui.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.challenge.hwg.R
import com.challenge.hwg.ui.theme.HWGCodeChallengeTheme

@Composable
fun Rating(
    rating: String,
    numberOfRatings: String?, // shown when supplied, not needed in some use cases
    modifier: Modifier = Modifier,
    mediumSizeRatingsText: Boolean = false // adjust size of [numberOfRatings]
) {
    Row(
        modifier = modifier.padding(vertical = 5.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_star),
            contentDescription = stringResource(R.string.back_button),
            tint = Color.Unspecified
        )
        Spacer(modifier = Modifier.width(10.dp))
        Text(
            text = rating,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )
        numberOfRatings?.let {
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = stringResource(id = R.string.parenthesise, it),
                style = when (mediumSizeRatingsText) {
                    true -> MaterialTheme.typography.bodyMedium
                    false -> MaterialTheme.typography.bodySmall
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRating() {
    HWGCodeChallengeTheme {
        Rating(rating = "9.7", numberOfRatings = "1234")
    }
}