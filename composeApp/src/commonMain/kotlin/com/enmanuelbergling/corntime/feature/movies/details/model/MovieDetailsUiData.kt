package com.enmanuelbergling.corntime.feature.movies.details.model

import com.enmanuelbergling.corntime.core.model.movie.MovieCredits
import com.enmanuelbergling.corntime.core.model.movie.MovieDetails

data class MovieDetailsUiData(
    val details: MovieDetails? = null,
    val credits: MovieCredits? = null,
    val movieId: Int = 0,
) {
    val skipDetails get() = details != null
    val skipCredits get() = credits != null
}