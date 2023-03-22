package com.example.amphibians.ui.elements

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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