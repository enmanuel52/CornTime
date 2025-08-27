package com.enmanuelbergling.corntime.datasource.remote

import com.enmanuelbergling.corntime.data.FakeActorData
import com.enmanuelbergling.corntime.data.FakeMovieData
import com.enmanuelbergling.corntime.core.domain.datasource.remote.ActorRemoteDS
import com.enmanuelbergling.corntime.core.model.actor.KnownMovie
import com.enmanuelbergling.corntime.core.model.core.ResultHandler
import com.enmanuelbergling.corntime.core.model.core.asPage

class FakeActorRemoteDS : ActorRemoteDS {
    override suspend fun getActorDetails(id: Int) =
        ResultHandler.Success(FakeActorData.ACTOR_DETAILS)

    override suspend fun getMoviesByActor(actorId: Int) =
        ResultHandler.Success(FakeMovieData.MOVIES.map { movie ->
            KnownMovie(
                id = movie.id,
                posterPath = movie.posterPath,
                title = movie.title,
                voteAverage = movie.voteAverage
            )
        })

    override suspend fun getPopularActors(page: Int) =
        ResultHandler.Success(data = listOf(FakeActorData.ACTOR).asPage())
}