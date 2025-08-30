package com.enmanuelbergling.corntime.feature.movies.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.enmanuelbergling.corntime.core.model.core.NetworkException
import com.enmanuelbergling.corntime.core.model.core.SimplerUi
import com.enmanuelbergling.corntime.core.ui.components.messageResource
import com.enmanuelbergling.corntime.feature.movies.home.model.MoviesChainStart
import com.enmanuelbergling.corntime.feature.movies.home.model.MoviesUiData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MoviesVM(
    private val homeMoviesHandler: MoviesChainStart,
) : ViewModel() {

    private val _uiState = MutableStateFlow<SimplerUi>(SimplerUi.Idle)
    val uiState = _uiState.asStateFlow()

    private val _uiDataState = MutableStateFlow(MoviesUiData())
    val uiDataState get() = _uiDataState.asStateFlow()

    init {
        loadUi()
    }

    fun loadUi() = viewModelScope.launch {
        _uiState.update { SimplerUi.Loading }
        runCatching {
            homeMoviesHandler.invoke(_uiDataState)
        }.onFailure { _ ->
            _uiState.update { SimplerUi.Error(NetworkException.ReadTimeOutException.messageResource) }
        }.onSuccess {
            _uiState.update { SimplerUi.Idle }
        }
    }

}