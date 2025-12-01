package com.enmanuelbergling.feature.movies.paging.usecase

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.enmanuelbergling.core.domain.datasource.remote.MovieRemoteDS
import com.enmanuelbergling.feature.movies.paging.source.RecommendednMovieSource

internal class GetRecommendedMoviesUC(
    private val remoteDS: MovieRemoteDS,
) {
    fun invoke(movieId: Int) = Pager(
        config = PagingConfig(pageSize = 20, enablePlaceholders = false),
        pagingSourceFactory = { RecommendednMovieSource(remoteDS, movieId) }
    ).flow
}