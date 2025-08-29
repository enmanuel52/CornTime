package com.enmanuelbergling.corntime.core.data.remote.ktorfit.service

import com.enmanuelbergling.corntime.core.data.remote.dto.movie.MoviePageDTO
import de.jensklingenberg.ktorfit.http.GET
import de.jensklingenberg.ktorfit.http.Query

internal interface MoviesSearchService {

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("query") query: String,
        @Query("page") page: Int,
    ): MoviePageDTO
}