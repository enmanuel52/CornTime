package com.enmanuelbergling.corntime.core.domain.usecase.movie

import com.enmanuelbergling.corntime.core.domain.datasource.remote.MovieRemoteDS

class GetMovieDetailsUC(
    private val dataSource: MovieRemoteDS
) {
    suspend operator fun invoke(id: Int) = dataSource.getMovieDetails(id)
}