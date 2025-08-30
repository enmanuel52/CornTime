package com.enmanuelbergling.corntime.feature.movies.home.model

import com.enmanuelbergling.corntime.core.domain.design.CannotHandleException
import com.enmanuelbergling.corntime.core.domain.design.ChainHandler
import com.enmanuelbergling.corntime.core.domain.usecase.movie.GetNowPlayingMoviesUC
import com.enmanuelbergling.corntime.core.model.core.ResultHandler
import kotlinx.coroutines.flow.update

class NowPlayingMoviesChainHandler(
    private val getNowPlayingMoviesUC: GetNowPlayingMoviesUC,
    private val next: PopularMoviesChainHandler,
) : ChainHandler<MoviesUiState> {
    override val nextChainHandler: ChainHandler<MoviesUiState>
        get() = next

    override suspend fun handle(request: MoviesUiState) =
        if (request.value.skipNowPlaying) Unit
        else when (val result = getNowPlayingMoviesUC()) {
            is ResultHandler.Error -> throw CannotHandleException(result.exception.message.orEmpty())
            is ResultHandler.Success -> request.update {
                it.copy(nowPlaying = result.data?.results.orEmpty())
            }
        }
}