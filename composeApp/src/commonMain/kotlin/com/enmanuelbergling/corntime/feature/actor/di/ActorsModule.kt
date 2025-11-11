package com.enmanuelbergling.corntime.feature.actor.di

import com.enmanuelbergling.corntime.feature.actor.details.ActorDetailsVM
import com.enmanuelbergling.corntime.feature.actor.details.model.ActorDetailsChainHandler
import com.enmanuelbergling.corntime.feature.actor.details.model.ActorDetailsChainStart
import com.enmanuelbergling.corntime.feature.actor.details.model.ActorKnownMoviesChainHandler
import com.enmanuelbergling.corntime.feature.actor.home.ActorsVM
import com.enmanuelbergling.corntime.feature.actor.paging.GetPopularActorsUC
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val actorDetailsScreenModule = module {
    singleOf(::ActorDetailsChainHandler)
    singleOf(::ActorKnownMoviesChainHandler)
    singleOf(::ActorDetailsChainStart)
}

val actorsModule = module {
    includes(actorDetailsScreenModule)
    singleOf(::GetPopularActorsUC)

    viewModelOf(::ActorsVM)
    viewModelOf(::ActorDetailsVM)
}