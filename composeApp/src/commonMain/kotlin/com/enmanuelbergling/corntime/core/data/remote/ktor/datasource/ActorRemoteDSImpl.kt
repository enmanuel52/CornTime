package com.enmanuelbergling.corntime.core.data.remote.ktor.datasource

import com.enmanuelbergling.corntime.core.data.remote.ktor.service.ActorService
import com.enmanuelbergling.corntime.core.data.remote.mappers.toModel
import com.enmanuelbergling.corntime.core.domain.datasource.remote.ActorRemoteDS
import com.enmanuelbergling.corntime.core.model.actor.Actor
import com.enmanuelbergling.corntime.core.model.actor.ActorDetails
import com.enmanuelbergling.corntime.core.model.actor.KnownMovie
import com.enmanuelbergling.corntime.core.model.core.PageModel
import com.enmanuelbergling.corntime.core.model.core.ResultHandler

internal class ActorRemoteDSImpl(private val service: ActorService) : ActorRemoteDS {
    override suspend fun getActorDetails(id: Int): ResultHandler<ActorDetails> = safeKtorCall {
        service.getActorDetails(id).toModel()
    }

    override suspend fun getMoviesByActor(actorId: Int): ResultHandler<List<KnownMovie>> =
        safeKtorCall {
            service.getMoviesByActor(actorId).cast.map { it.toModel() }
        }

    override suspend fun getPopularActors(page: Int): ResultHandler<PageModel<Actor>> =
        safeKtorCall {
            val result = service.getPopularActors(page)
            val actors = result.results.map { it.toModel() }

            PageModel(actors, result.totalPages)
        }
}