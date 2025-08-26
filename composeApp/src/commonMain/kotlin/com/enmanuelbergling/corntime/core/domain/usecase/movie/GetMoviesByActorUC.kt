package com.enmanuelbergling.corntime.core.domain.usecase.movie

import com.enmanuelbergling.corntime.core.domain.datasource.remote.ActorRemoteDS

class GetMoviesByActorUC(
    private val remoteDS: ActorRemoteDS
){
    suspend operator fun invoke(actorId:Int) =  remoteDS.getMoviesByActor(actorId)
}