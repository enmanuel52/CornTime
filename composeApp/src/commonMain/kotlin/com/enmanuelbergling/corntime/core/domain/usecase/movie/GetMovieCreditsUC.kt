package com.enmanuelbergling.corntime.core.domain.usecase.movie

import com.enmanuelbergling.corntime.core.domain.datasource.remote.MovieRemoteDS

class GetMovieCreditsUC(
    private val remoteDS: MovieRemoteDS
) {
    suspend operator fun invoke(id: Int) = remoteDS.getMovieCredits(id)
}