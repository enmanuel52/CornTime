package com.enmanuelbergling.feature.movies.recommended

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.enmanuelbergling.feature.movies.paging.usecase.GetRecommendedMoviesUC

internal class RecommendedMoviesVM(
    getRecommendedMoviesUC: GetRecommendedMoviesUC,
    movieId: Int,
) : ViewModel() {
    val movies = getRecommendedMoviesUC.invoke(movieId).cachedIn(viewModelScope)
}