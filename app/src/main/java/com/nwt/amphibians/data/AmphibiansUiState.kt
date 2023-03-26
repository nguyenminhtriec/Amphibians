package com.nwt.amphibians.data

interface AmphibiansUiState {
    data class Success(
        val amphibians: List<Amphibian>,
        val selectedAmphibian: Amphibian? = null
    ) : AmphibiansUiState
    object Loading : AmphibiansUiState
    object Error : AmphibiansUiState
}
