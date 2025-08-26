package com.enmanuelbergling.corntime.core.domain.usecase.movie

import com.enmanuelbergling.corntime.core.domain.datasource.remote.MovieRemoteDS

class GetTopRatedMoviesUC(
    private val remoteDS: MovieRemoteDS
) {
    suspend operator fun invoke() = remoteDS.getTopRatedMovies()
}