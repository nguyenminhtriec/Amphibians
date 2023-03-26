package com.nwt.amphibians.ui.screens

import android.app.Application
import androidx.compose.runtime.*
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nwt.amphibians.AmphibiansApplication
import com.nwt.amphibians.data.Amphibian
import com.nwt.amphibians.data.AmphibiansRepository
import com.nwt.amphibians.data.AmphibiansUiState
import com.nwt.amphibians.network.AmphibiansApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class AmphibiansViewModel(private  val amphibiansRepository: AmphibiansRepository) : ViewModel() {

    var _uiState: AmphibiansUiState by mutableStateOf(AmphibiansUiState.Loading)
        private set

    //private var _isShowingDetails by mutableStateOf(false)
    //val isShowingDetails: Boolean get() = _isShowingDetails

    init {
        getAmphibiansInfo()
    }

    private fun getAmphibiansInfo() {

        viewModelScope.launch {
            try {
                val result: List<Amphibian> = amphibiansRepository.getAmphibiansInfo()
                _uiState = AmphibiansUiState.Success(result)
            } catch (e: HttpException) {
                _uiState = AmphibiansUiState.Error

            } catch(e: IOException) {
                _uiState = AmphibiansUiState.Error
            }
        }
    }

    // companion object for ViewModelProvider
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val amphibiansApplication = (this[APPLICATION_KEY] as AmphibiansApplication)
                val amphibiansRepository = amphibiansApplication.appContainer.amphibiansRepository
                AmphibiansViewModel(amphibiansRepository = amphibiansRepository)
            }
        }
    }
}

