package com.nwt.amphibians.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nwt.amphibians.data.Amphibian
import com.nwt.amphibians.data.AmphibiansUiState

@Composable
fun AmphibiansHome(
    uiState: AmphibiansUiState,
    modifier: Modifier = Modifier


) {
    when (uiState) {
        is AmphibiansUiState.Loading -> LoadingScreen()
        is AmphibiansUiState.Error -> ErrorScreen()
        is AmphibiansUiState.Success -> AmphibianList(
            amphibians = uiState.amphibians
        )
    }
}

@Composable
fun AmphibianList(
    amphibians: List<Amphibian>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth().padding(top = 8.dp)
    ) {
        items(amphibians) { amphibian ->
            AmphibiansCard(
                amphibian = amphibian,
            )
        }
    }
}

@Composable
fun LoadingScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(com.nwt.amphibians.R.drawable.loading_img),
            contentDescription = null,
            modifier = modifier.size(100.dp)
        )
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(com.nwt.amphibians.R.drawable.ic_connection_error),
            contentDescription = null,
            modifier = modifier.size(100.dp)
        )
    }
}

