package com.nwt.amphibians.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nwt.amphibians.R
import com.nwt.amphibians.data.Amphibian
import com.nwt.amphibians.ui.theme.AmphibiansTheme


@Composable
fun AmphibiansCard(
    amphibian: Amphibian,
    modifier: Modifier = Modifier,
) {
    var isShowingDetails by remember { mutableStateOf(false) }
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        HeaderCard(
            amphibian = amphibian,
            onHeaderClicked = { isShowingDetails = !isShowingDetails }
        )
        Spacer(modifier = modifier.height(8.dp))
        if(isShowingDetails) {
            DetailCard(amphibian = amphibian)
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun HeaderCard(
    amphibian: Amphibian,
    onHeaderClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Card(
        onClick = onHeaderClicked,
        modifier = modifier.fillMaxWidth(),
        elevation = 4.dp
    ) {
        Row(
            modifier = modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                modifier = modifier.size(36.dp),
                contentScale = ContentScale.Crop
            )
            Text(
                text = "${amphibian.name} -- ${amphibian.type}",
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun DetailCard(
    amphibian: Amphibian,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = 4.dp
    ) {
        Column(
            modifier = modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(amphibian.description)
            Spacer(modifier = modifier.height(16.dp))
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(amphibian.imgSrc)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                error = painterResource(R.drawable.ic_broken_image),
                placeholder = painterResource(R.drawable.loading_img),
                contentScale = ContentScale.FillBounds
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    AmphibiansTheme {
        //AmphibiansCard(amphibian = ampList[0] )
    }
}
