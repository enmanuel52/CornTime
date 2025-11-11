package com.enmanuelbergling.corntime.feature.actor.details.model

import com.enmanuelbergling.corntime.core.domain.design.CannotHandleException
import com.enmanuelbergling.corntime.core.domain.design.ChainHandler
import com.enmanuelbergling.corntime.core.domain.usecase.movie.GetMoviesByActorUC
import com.enmanuelbergling.corntime.core.model.core.ResultHandler
import kotlinx.coroutines.flow.update

class ActorKnownMoviesChainHandler(
    private val getMoviesByActorUC: GetMoviesByActorUC,
) : ChainHandler<ActorDetailsUiState> {
    override val nextChainHandler: ChainHandler<ActorDetailsUiState>?
        get() = null

    override suspend fun handle(request: ActorDetailsUiState) =
        if (request.value.skipKnownMovies) Unit
        else when (val result = getMoviesByActorUC(request.value.actorId)) {
            is ResultHandler.Error -> throw CannotHandleException(result.exception.message.orEmpty())
            is ResultHandler.Success -> request.update {
                it.copy(knownMovies = result.data.orEmpty())
            }
        }
}