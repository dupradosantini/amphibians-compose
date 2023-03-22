package com.example.amphibians.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.amphibians.ui.theme.AmphibiansTheme

@Composable
fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier) {
    Surface(color = MaterialTheme.colorScheme.secondary) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Loading failed.",
                color = MaterialTheme.colorScheme.onBackground)
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = retryAction) {
                Text("Retry")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorPreview(){
    AmphibiansTheme {
        ErrorScreen(retryAction = {  }, modifier = Modifier )
    }
}