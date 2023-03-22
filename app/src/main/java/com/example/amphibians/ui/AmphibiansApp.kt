package com.example.amphibians.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.amphibians.ui.screens.AmphibiansViewModel
import com.example.amphibians.ui.screens.HomeScreen

@Composable
fun AmphibiansApp(modifier: Modifier = Modifier){

    val amphibiansViewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)

    HomeScreen(
        amphibiansUiState = amphibiansViewModel.amphibiansUiState,
        retryAction = {amphibiansViewModel.getAmphibians()},
        modifier = modifier
    )
}