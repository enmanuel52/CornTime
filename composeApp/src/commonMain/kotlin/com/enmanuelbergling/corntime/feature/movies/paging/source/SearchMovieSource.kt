package com.enmanuelbergling.corntime.feature.movies.paging.source

import com.enmanuelbergling.corntime.core.domain.datasource.remote.MovieRemoteDS
import com.enmanuelbergling.corntime.core.model.core.PageModel
import com.enmanuelbergling.corntime.core.model.core.ResultHandler
import com.enmanuelbergling.corntime.core.model.movie.Movie
import com.enmanuelbergling.corntime.core.model.movie.QueryString
import com.enmanuelbergling.corntime.core.ui.core.GenericPagingSource

internal class SearchMovieSource(service: MovieRemoteDS, query: QueryString) :
    GenericPagingSource<Movie>(
        request = { page ->
            when (val result = service.searchMovie(query.query, page)) {
                is ResultHandler.Error -> PageModel(emptyList(), 0)
                is ResultHandler.Success -> result.data ?: PageModel(emptyList(), 0)
            }
        }
    )