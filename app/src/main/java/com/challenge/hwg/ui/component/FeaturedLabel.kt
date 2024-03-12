package com.challenge.hwg.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.challenge.hwg.R
import com.challenge.hwg.ui.theme.Purple

@Composable
fun FeaturedLabel(
    type: String,
    modifier: Modifier = Modifier
) {
    Text(
        // modifier.then() keeps current style and adds new ones, does not override
        modifier = modifier.then(
            Modifier
                .background(Purple)
                .padding(horizontal = 10.dp)
                .testTag(TestTags.FeaturedLabel)
        ),
        text = stringResource(id = R.string.featured, type).uppercase(),
        style = MaterialTheme.typography.bodyMedium,
        color = Color.White
    )
}

@Preview
@Composable
private fun PreviewFeaturedLabel() {
    MaterialTheme {
        FeaturedLabel("HOTEL")
    }
}