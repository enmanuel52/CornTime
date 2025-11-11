package com.enmanuelbergling.corntime.di

import com.enmanuelbergling.corntime.feature.actor.di.actorsModule
import com.enmanuelbergling.corntime.feature.movies.di.moviesModule
import com.enmanuelbergling.corntime.feature.settings.di.settingsModule
import com.enmanuelbergling.corntime.feature.watchlists.di.watchlistModule
import com.enmanuelbergling.corntime.ui.CornTimeVM
import com.enmanuelbergling.corntime.feature.auth.di.loginModule
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featuresModule = module {
    includes(
        listOf(
            moviesModule,
            actorsModule,
            watchlistModule,
            settingsModule
        ) + loginModule
    )

    viewModelOf(::CornTimeVM)
}