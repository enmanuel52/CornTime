package com.enmanuelbergling.feature.movies.paging.source

import com.enmanuelbergling.core.domain.datasource.remote.MovieRemoteDS
import com.enmanuelbergling.core.model.core.PageModel
import com.enmanuelbergling.core.model.core.ResultHandler
import com.enmanuelbergling.core.model.movie.Movie
import com.enmanuelbergling.core.ui.core.GenericPagingSource

internal class RecommendednMovieSource(service: MovieRemoteDS, movieId: Int) :
    GenericPagingSource<Movie>(
        request = {
            val result = service.getRecommendations(movieId, it)
            val emptyPage = PageModel<Movie>(emptyList(), 0)

            when (result) {
                is ResultHandler.Error<*> -> emptyPage
                is ResultHandler.Success -> result.data ?: emptyPage
            }
        }
    )