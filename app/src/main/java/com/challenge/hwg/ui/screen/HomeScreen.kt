package com.challenge.hwg.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.challenge.hwg.presentation.PropertyListState
import com.challenge.hwg.ui.PropertiesRoute
import com.challenge.hwg.ui.PropertyDetailsRoute
import com.challenge.hwg.ui.component.ProgressSpinner
import com.challenge.hwg.ui.navigateToPropertyDetails

@Composable
fun HomeScreen(
    navController: NavHostController,
    uiState: PropertyListState,
    refresh: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (uiState) {
        is PropertyListState.Loading -> {
            // progress bar when loading data from remote
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                ProgressSpinner()
            }
        }

        is PropertyListState.Error -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    // error message
                    Text(
                        text = uiState.message,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                    // Refresh icon to refresh the page and try again
                    Icon(
                        imageVector = Icons.Outlined.Refresh,
                        contentDescription = "refresh",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(64.dp)
                            .clickable(onClick = refresh)
                    )
                }
            }
        }

        is PropertyListState.Success -> {
            NavHost(
                navController = navController,
                startDestination = PropertiesRoute,
                modifier = modifier.fillMaxSize()
            ) {
                composable(route = PropertiesRoute) {
                    PropertiesScreen(
                        city = uiState.city,
                        properties = uiState.properties,
                        onPropertyClick = navController::navigateToPropertyDetails,
                        refresh = refresh
                    )
                }

                composable(route = PropertyDetailsRoute) {
                    // get index of selected property from nav arguments
                    val index = it.arguments?.getString("index")!!.toInt()
                    PropertyDetailsScreen(
                        city = uiState.city,
                        property = uiState.properties[index],
                        back = navController::navigateUp,
                    )
                }
            }
        }
    }
}