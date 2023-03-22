package com.example.amphibians.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.amphibians.R
import com.example.amphibians.model.Amphibian
import com.example.amphibians.ui.elements.AmphibianCard
import com.example.amphibians.ui.elements.AmphibiansSelectionTab
import com.example.amphibians.ui.elements.AmphibiansTopAppBar
import com.example.amphibians.ui.theme.AmphibiansTheme


@Composable
fun AmphibiansLoadedScreen(
    amphibianEntries: List<Amphibian>,
    modifier: Modifier
) {
    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.secondary
    ) {
        Column {
            AmphibiansTopAppBar()
            Spacer(modifier = Modifier.height(16.dp))
            ChooseYourCritterTitle()

            val titleList = amphibianEntries.map { it.name }

            var selectedTabIndex by remember { mutableStateOf(0) }

            AmphibiansSelectionTab(
                onTabSelected = {
                    selectedTabIndex = it
                },
                selectedTabIndex = selectedTabIndex,
                listOfTitles = titleList
            )
            AmphibianCard(
                title = amphibianEntries[selectedTabIndex].name,
                description = amphibianEntries[selectedTabIndex].description,
                imageResource = amphibianEntries[selectedTabIndex].imgSrc
            )
        }
    }
}
@Composable
fun ChooseYourCritterTitle() {
    Card(
        elevation = CardDefaults.cardElevation(4.dp),
        modifier = Modifier
            .padding(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = stringResource(R.string.text_choose_critter),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth()
            )
        }
    }
}

const val descriptionFiller = "This toad is typically found in South America. " +
        "Specifically on Mount Roraima at the borders of Venezuela, Brazil, and Guyana, " +
        "hence the name. The Roraimam Bush Toad is typically black with yellow spots or " +
        "marbling along the throat and belly"

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val amphibian = Amphibian(
        name = "frog",
        type = "frog",
        description = descriptionFiller,
        imgSrc = ""
    )
    AmphibiansTheme {
        AmphibiansLoadedScreen(amphibianEntries = listOf(amphibian), modifier = Modifier)
    }
}

