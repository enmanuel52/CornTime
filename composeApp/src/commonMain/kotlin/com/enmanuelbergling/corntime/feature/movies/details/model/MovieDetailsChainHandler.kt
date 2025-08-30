package com.enmanuelbergling.corntime.feature.movies.details.model

import com.enmanuelbergling.corntime.core.domain.design.CannotHandleException
import com.enmanuelbergling.corntime.core.domain.design.ChainHandler
import com.enmanuelbergling.corntime.core.domain.usecase.movie.GetMovieDetailsUC
import com.enmanuelbergling.corntime.core.model.core.ResultHandler
import kotlinx.coroutines.flow.update

class MovieDetailsChainHandler(
    private val getMovieDetailsUC: GetMovieDetailsUC,
    private val nextHandler: CreditsChainHandler,
) : ChainHandler<MovieDetailsUiState> {
    override val nextChainHandler: ChainHandler<MovieDetailsUiState>
        get() = nextHandler

    override suspend fun handle(request: MovieDetailsUiState) =
        if (request.value.skipDetails) Unit
        else when (val result = getMovieDetailsUC(request.value.movieId)) {
            is ResultHandler.Error -> throw CannotHandleException(result.exception.message.orEmpty())
            is ResultHandler.Success -> request.update {
                it.copy(details = result.data)
            }
        }
}