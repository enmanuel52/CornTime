package com.enmanuelbergling.corntime.core.domain.datasource.remote

import com.enmanuelbergling.corntime.core.model.core.PageModel
import com.enmanuelbergling.corntime.core.model.core.ResultHandler
import com.enmanuelbergling.corntime.core.model.movie.*

interface MovieRemoteDS : RemoteDataSource {

    suspend fun getMovieDetails(id: Int): ResultHandler<MovieDetails>

    suspend fun getMovieCredits(id: Int): ResultHandler<MovieCredits>

    suspend fun getNowPlayingMovies(page: Int = 1): ResultHandler<PageModel<Movie>>

    suspend fun getUpcomingMovies(page: Int = 1): ResultHandler<PageModel<Movie>>


    suspend fun getTopRatedMovies(page: Int = 1): ResultHandler<PageModel<Movie>>

    suspend fun getPopularMovies(page: Int = 1): ResultHandler<PageModel<Movie>>
    suspend fun getMovieGenres(): ResultHandler<List<Genre>>

    suspend fun getMoviesByGenre(
        genres: String,
        sortBy: String,
        page: Int,
    ): ResultHandler<PageModel<Movie>>

    suspend fun searchMovie(
        query: String,
        page: Int,
    ): ResultHandler<PageModel<Movie>>
}