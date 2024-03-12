package com.challenge.hwg.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.challenge.hwg.R
import com.challenge.hwg.ui.theme.HWGCodeChallengeTheme
import com.challenge.model.Price

@Composable
fun PriceFrom(
    price: Price,
    modifier: Modifier = Modifier,
    alignEnd: Boolean = false
) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.align(if (alignEnd) Alignment.End else Alignment.Start),
            text = stringResource(id = R.string.from),
            style = MaterialTheme.typography.bodyMedium
        )
        Text(
            modifier = Modifier
                .align(if (alignEnd) Alignment.End else Alignment.Start)
                .testTag(TestTags.PriceValue),
            text = "$price",
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewPriceFrom() {
    HWGCodeChallengeTheme {
        PriceFrom(price = Price("67.89"))
    }
}