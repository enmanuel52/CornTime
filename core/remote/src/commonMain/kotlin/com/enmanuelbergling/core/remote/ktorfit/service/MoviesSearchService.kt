package com.enmanuelbergling.core.remote.ktorfit.service

import com.enmanuelbergling.core.remote.dto.movie.MoviePageDTO
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

internal interface MoviesSearchService {

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("page") page: Int,
    ): MoviePageDTO
}