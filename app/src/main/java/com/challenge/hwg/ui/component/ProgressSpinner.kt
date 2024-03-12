package com.challenge.hwg.ui.component

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.challenge.hwg.ui.theme.HWGCodeChallengeTheme

@Composable
fun ProgressSpinner(modifier: Modifier = Modifier) {
    CircularProgressIndicator(
        // modifier.then() allows caller to specify the size of the spinner
        // only sets to 64.dp when size has not been specified via modifier
        modifier = modifier
            .then(Modifier.size(64.dp))
            .testTag(TestTags.ProgressSpinner),
        color = MaterialTheme.colorScheme.primary,
        trackColor = MaterialTheme.colorScheme.surfaceVariant,
    )
}

@Preview
@Composable
private fun PreviewProgressSpinner() {
    HWGCodeChallengeTheme {
        ProgressSpinner()
    }
}