package com.enmanuelbergling.corntime.di

import com.enmanuelbergling.corntime.feature.movies.di.moviesModule
import com.enmanuelbergling.corntime.ui.CornTimeVM
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val featuresModule = module {
    includes(
        listOf(
            moviesModule,
//            actorsModule,
//            watchlistModule,
//            settingsModule
        )
//                + loginModule
    )

    viewModelOf(::CornTimeVM)
}