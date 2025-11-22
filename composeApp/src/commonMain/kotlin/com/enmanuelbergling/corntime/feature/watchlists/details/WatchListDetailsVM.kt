package com.enmanuelbergling.corntime.feature.watchlists.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.enmanuelbergling.core.domain.usecase.auth.GetSavedSessionIdUC
import com.enmanuelbergling.core.domain.usecase.user.watchlist.DeleteMovieFromListUC
import com.enmanuelbergling.core.model.core.ResultHandler
import com.enmanuelbergling.corntime.core.model.SimplerUi
import com.enmanuelbergling.corntime.core.ui.components.messageResource
import com.enmanuelbergling.corntime.feature.watchlists.paging.GetWatchListMoviesUC
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class WatchListDetailsVM(
    getWatchListMovies: GetWatchListMoviesUC,
    private val getSessionId: com.enmanuelbergling.core.domain.usecase.auth.GetSavedSessionIdUC,
    private val deleteMovieFromListUC: com.enmanuelbergling.core.domain.usecase.user.watchlist.DeleteMovieFromListUC,
    private val listId: Int,
) : ViewModel() {
    private val _uiState = MutableStateFlow<SimplerUi>(SimplerUi.Idle)
    val uiState = _uiState.asStateFlow()

    val movies = getWatchListMovies(listId).cachedIn(viewModelScope)

    fun deleteMovieFromList(
        movieId: Int,
    ) = viewModelScope.launch {
        val sessionId = getSessionId().firstOrNull().orEmpty()
        _uiState.update { SimplerUi.Loading }
        when (val result = deleteMovieFromListUC(
            movieId = movieId,
            listId = listId,
            sessionId = sessionId
        )) {
            is ResultHandler.Error -> _uiState.update { SimplerUi.Error(result.exception.messageResource) }
            is ResultHandler.Success -> {
                _uiState.update { SimplerUi.Success }
            }
        }
    }

    fun onIdle() {
        _uiState.update { SimplerUi.Idle }
    }
}