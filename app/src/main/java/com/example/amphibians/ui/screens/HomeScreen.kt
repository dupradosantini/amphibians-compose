package com.example.amphibians.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(
    amphibiansUiState: AmphibiansUiState,
    retryAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    when (amphibiansUiState) {
        is AmphibiansUiState.Loading -> LoadingScreen(modifier)
        is AmphibiansUiState.Error -> ErrorScreen(retryAction, modifier)
        is AmphibiansUiState.Success -> AmphibiansLoadedScreen(amphibiansUiState.entries, modifier)
    }
}