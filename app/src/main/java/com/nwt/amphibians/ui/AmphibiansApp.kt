package com.nwt.amphibians.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nwt.amphibians.AmphibiansApplication
import com.nwt.amphibians.ui.screens.AmphibiansHome
import com.nwt.amphibians.ui.screens.AmphibiansViewModel

@Composable
fun AmphibiansApp(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = { Text(text = stringResource(com.nwt.amphibians.R.string.app_name)) })
        }
    ) {

        val viewModel: AmphibiansViewModel = viewModel(factory = AmphibiansViewModel.Factory)

        AmphibiansHome(
            uiState = viewModel._uiState,
            modifier = modifier.padding(it)
        )
    }
}