package com.enmanuelbergling.corntime.feature.watchlists.di

import com.enmanuelbergling.corntime.feature.watchlists.details.WatchListDetailsVM
import com.enmanuelbergling.corntime.feature.watchlists.home.WatchListVM
import com.enmanuelbergling.corntime.feature.watchlists.paging.GetWatchListMoviesUC
import com.enmanuelbergling.corntime.feature.watchlists.paging.GetUserWatchListsUC
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val watchlistModule = module {
    singleOf(::GetWatchListMoviesUC)
    singleOf(::GetUserWatchListsUC)

    viewModelOf(::WatchListVM)
    viewModelOf(::WatchListDetailsVM)
}