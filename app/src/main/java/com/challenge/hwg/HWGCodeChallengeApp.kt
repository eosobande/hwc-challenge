package com.challenge.hwg

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rxjava2.subscribeAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.challenge.hwg.presentation.AppViewModel
import com.challenge.hwg.presentation.PropertyListState
import com.challenge.hwg.ui.screen.HomeScreen

@Composable
fun HWGCodeChallengeApp(
    navController: NavHostController = rememberNavController(),
    viewModel: AppViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.subscribeAsState(PropertyListState.Loading)
    HomeScreen(navController, uiState = uiState, refresh = viewModel::refresh)
}

