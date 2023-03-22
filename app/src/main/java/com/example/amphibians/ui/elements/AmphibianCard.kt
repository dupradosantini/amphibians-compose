package com.example.amphibians.ui.elements

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest

@Composable
fun AmphibianCard(
    modifier: Modifier = Modifier,
    title: String,
    description: String,
    imageResource: String
) {
    Card(
        modifier = modifier
            .padding(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background,
            contentColor = MaterialTheme.colorScheme.onBackground
        )

    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier
                    .weight(0.5f)
                    .padding(16.dp),
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )

            val shimmerColors = listOf(
                Color.LightGray.copy(alpha = 0.6f),
                Color.LightGray.copy(alpha = 0.2f),
                Color.LightGray.copy(alpha = 0.6f)
            )

            val transition = rememberInfiniteTransition()

            val translateAnimation = transition.animateFloat(
                initialValue = 0f,
                targetValue = 1000f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = 1000,
                        easing = FastOutSlowInEasing
                    ),
                )
            )

            val brush = Brush.linearGradient(
                colors = shimmerColors,
                start =  Offset.Zero,
                end = Offset(x= translateAnimation.value, y= translateAnimation.value),
            )


            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageResource)
                    .crossfade(500)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .background(brush)
            )
        }
    }
}