package com.enmanuelbergling.corntime.feature.movies.paging.source

import com.enmanuelbergling.corntime.core.domain.datasource.remote.MovieRemoteDS
import com.enmanuelbergling.corntime.core.model.core.PageModel
import com.enmanuelbergling.corntime.core.model.core.ResultHandler
import com.enmanuelbergling.corntime.core.model.movie.Movie
import com.enmanuelbergling.corntime.core.ui.core.GenericPagingSource

internal class NowPlayingMovieSource(remoteDS: MovieRemoteDS) :
    GenericPagingSource<Movie>(
        request = { page ->
            when (val result = remoteDS.getNowPlayingMovies(page)) {
                is ResultHandler.Error -> PageModel(emptyList(), 0)
                is ResultHandler.Success -> result.data ?: PageModel(emptyList(), 0)
            }
        }
    )