package com.enmanuelbergling.corntime.feature.actor.details.model

import com.enmanuelbergling.corntime.core.domain.design.CannotHandleException
import com.enmanuelbergling.corntime.core.domain.design.ChainHandler
import com.enmanuelbergling.corntime.core.domain.usecase.movie.GetActorDetailsUC
import com.enmanuelbergling.corntime.core.model.core.ResultHandler
import kotlinx.coroutines.flow.update

class ActorDetailsChainHandler(
    private val nextHandler: ActorKnownMoviesChainHandler,
    private val getActorDetailsUC: GetActorDetailsUC,
) : ChainHandler<ActorDetailsUiState> {
    override val nextChainHandler: ChainHandler<ActorDetailsUiState>
        get() = nextHandler

    override suspend fun handle(request: ActorDetailsUiState) =
        if (request.value.skipDetails) Unit
        else when (val result = getActorDetailsUC(request.value.actorId)) {
            is ResultHandler.Error -> throw CannotHandleException(result.exception.message.orEmpty())
            is ResultHandler.Success -> request.update {
                it.copy(details = result.data)
            }
        }
}