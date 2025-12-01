package com.enmanuelbergling.corntime.navigation

import androidx.navigation3.runtime.NavKey
import androidx.savedstate.serialization.SavedStateConfiguration
import com.enmanuelbergling.feature.actor.navigation.ActorDetailsDestination
import com.enmanuelbergling.feature.actor.navigation.ActorsDestination
import com.enmanuelbergling.feature.auth.navigation.LoginDestination
import com.enmanuelbergling.feature.movies.navigation.MovieSearchDestination
import com.enmanuelbergling.feature.movies.navigation.MoviesDestination
import com.enmanuelbergling.feature.movies.navigation.MoviesDetailsDestination
import com.enmanuelbergling.feature.movies.navigation.MoviesFilterDestination
import com.enmanuelbergling.feature.movies.navigation.MoviesSectionDestination
import com.enmanuelbergling.feature.series.navigation.SeriesDestination
import com.enmanuelbergling.feature.settings.navigation.SettingsDestination
import com.enmanuelbergling.feature.watchlists.navigation.ListDetailsDestination
import com.enmanuelbergling.feature.watchlists.navigation.WatchListDestination
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

internal val NavConfiguration = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(MoviesDestination::class, MoviesDestination.serializer())
            subclass(
                MoviesDetailsDestination::class,
                MoviesDetailsDestination.serializer()
            )
            subclass(
                MoviesSectionDestination::class,
                MoviesSectionDestination.serializer()
            )
            subclass(MovieSearchDestination::class, MovieSearchDestination.serializer())
            subclass(MoviesFilterDestination::class, MoviesFilterDestination.serializer())

            subclass(SeriesDestination::class, SeriesDestination.serializer())

            subclass(ActorsDestination::class, ActorsDestination.serializer())
            subclass(
                ActorDetailsDestination::class,
                ActorDetailsDestination.serializer()
            )

            subclass(LoginDestination::class, LoginDestination.serializer())

            subclass(WatchListDestination::class, WatchListDestination.serializer())
            subclass(
                ListDetailsDestination::class,
                ListDetailsDestination.serializer()
            )

            subclass(SettingsDestination::class, SettingsDestination.serializer())
        }
    }
}
