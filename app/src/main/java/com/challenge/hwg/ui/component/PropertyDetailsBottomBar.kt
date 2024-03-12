package com.challenge.hwg.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import com.challenge.hwg.presentation.PropertyDetailsModel
import com.challenge.hwg.presentation.PropertyDetailsState
import com.challenge.model.Price
import com.challenge.model.Property

@Composable
fun PropertyDetailsBottomBar(
    uiState: PropertyDetailsState,
    modifier: Modifier = Modifier,
    convert: (Price.Currency) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(30.dp, 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Bottom
    ) {
        PriceFrom(price = uiState.price)
        when (uiState) {
            is PropertyDetailsState.Converting -> ProgressSpinner(Modifier.size(32.dp))
            else -> {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Price.Currency.entries.forEach {
                        Button(
                            enabled = uiState.price.currency != it,
                            contentPadding = PaddingValues(0.dp),
                            onClick = { convert(it) },
                            modifier = Modifier.testTag(it.name)
                        ) {
                            Text(
                                text = it.name,
                                style = MaterialTheme.typography.bodySmall,
                            )
                        }
                    }
                }
            }
        }
    }
}