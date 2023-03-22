package com.example.amphibians.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.amphibians.R
import com.example.amphibians.model.Amphibian
import com.example.amphibians.ui.theme.AmphibiansTheme

const val descriptionFiller = "This toad is typically found in South America. " +
        "Specifically on Mount Roraima at the borders of Venezuela, Brazil, and Guyana, " +
        "hence the name. The Roraimam Bush Toad is typically black with yellow spots or " +
        "marbling along the throat and belly"

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

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.secondary,
        modifier = modifier
    ) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ){
            Column{
                Text("Loading...",
                    color = MaterialTheme.colorScheme.onBackground)
                CircularProgressIndicator( modifier= Modifier.padding(16.dp))
            }
        }
    }
}

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
fun AmphibiansTopAppBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .background(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(bottomEnd = 8.dp, bottomStart = 8.dp)
            )
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.amphibians_logo),
            contentDescription = null,
            modifier = Modifier
                .size(96.dp)
                .padding(8.dp)
        )
        Text(
            text = stringResource(R.string.top_bar_title),
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleLarge
        )
    }
}

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

            //Manipulating the painter for a custom transition animation.
            /*val painter = rememberAsyncImagePainter(model = imageResource)
            if(painter.state is AsyncImagePainter.State.Loading) {
                CircularProgressIndicator()
            }

            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.onBackground)

            )*/

            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(imageResource)
                    .crossfade(500)
                    .build(),
                placeholder = painterResource(R.drawable.amphibians_logo),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .weight(2f)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colorScheme.onBackground)
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

@Composable
fun AmphibiansSelectionTab(
    onTabSelected: (Int) -> Unit,
    selectedTabIndex : Int,
    listOfTitles: List<String>,
    modifier: Modifier = Modifier
) {
    ScrollableTabRow(
        modifier = modifier,
        edgePadding = 12.dp,
        selectedTabIndex = selectedTabIndex,
        containerColor = MaterialTheme.colorScheme.secondary,
        indicator = { tabPositions: List<TabPosition> ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[selectedTabIndex])
                    .fillMaxSize()
                    .padding(horizontal = 2.dp)
                    .border(
                        BorderStroke(
                            2.dp,
                            MaterialTheme.colorScheme.primary
                        ),
                        RoundedCornerShape(10.dp)
                    )
            )
        },
        divider = { }
    ) {
        listOfTitles.forEachIndexed { index, title ->
            val selected = index == selectedTabIndex
            AmphibianTabText(
                amphibianTab = title,
                selected = selected,
                onTabSelected = onTabSelected,
                index = index
            )
        }
    }
}

@Composable
fun AmphibianTabText(
    amphibianTab: String,
    selected: Boolean,
    index: Int,
    onTabSelected: (Int) -> Unit
) {
    Tab(
        modifier = Modifier
            .padding(horizontal = 2.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(MaterialTheme.colorScheme.background),
        selected = selected,
        unselectedContentColor = MaterialTheme.colorScheme.tertiary,
        selectedContentColor = MaterialTheme.colorScheme.primary,
        onClick = {
            onTabSelected(index)
            println(index)
        }
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 6.dp),
            text = amphibianTab,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight(600),
                letterSpacing = 0.5.sp
            )
        )
    }
}


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

@Preview(showBackground = true)
@Composable
fun LoadingPreview(){
    AmphibiansTheme {
        LoadingScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorPreview(){
    AmphibiansTheme {
        ErrorScreen(retryAction = {  }, modifier = Modifier )
    }
}