package com.enmanuelbergling.corntime.di

import com.enmanuelbergling.feature.auth.di.loginModule
import com.enmanuelbergling.feature.settings.di.settingsModule
import com.enmanuelbergling.feature.watchlists.di.watchlistModule
import com.enmanuelbergling.corntime.ui.CornTimeVM
import com.enmanuelbergling.feature.actor.di.actorsModule
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featuresModule = module {
    includes(
        listOf(
            _root_ide_package_.com.enmanuelbergling.feature.movies.di.moviesModule,
            actorsModule,
            watchlistModule,
            settingsModule
        ) + loginModule
    )

    viewModelOf(::CornTimeVM)
}