package com.enmanuelbergling.corntime.core.domain.usecase.movie

import com.enmanuelbergling.corntime.core.domain.datasource.remote.MovieRemoteDS

class GetNowPlayingMoviesUC(
    private val remoteDS: MovieRemoteDS
) {
    suspend operator fun invoke() = remoteDS.getNowPlayingMovies()
}