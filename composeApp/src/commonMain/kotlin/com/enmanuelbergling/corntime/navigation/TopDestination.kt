package com.enmanuelbergling.corntime.navigation

import androidx.navigation3.runtime.NavKey
import com.enmanuelbergling.feature.actor.navigation.ActorsDestination
import com.enmanuelbergling.feature.movies.navigation.MoviesDestination
import com.enmanuelbergling.feature.series.navigation.SeriesDestination
import com.enmanuelbergling.feature.settings.navigation.SettingsDestination
import com.enmanuelbergling.feature.watchlists.navigation.WatchListDestination
import corntime.composeapp.generated.resources.Res
import corntime.composeapp.generated.resources.actors
import corntime.composeapp.generated.resources.bookmark_outline
import corntime.composeapp.generated.resources.bookmark_solid
import corntime.composeapp.generated.resources.check_badge_outline
import corntime.composeapp.generated.resources.check_badge_solid
import corntime.composeapp.generated.resources.cog_outline
import corntime.composeapp.generated.resources.cog_solid
import corntime.composeapp.generated.resources.film_outline
import corntime.composeapp.generated.resources.film_solid
import corntime.composeapp.generated.resources.movies
import corntime.composeapp.generated.resources.series
import corntime.composeapp.generated.resources.settings
import corntime.composeapp.generated.resources.tv_outline
import corntime.composeapp.generated.resources.tv_solid
import corntime.composeapp.generated.resources.watch_lists
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

enum class TopDestination(
     val label: StringResource,
     val selectedIconRes: DrawableResource,
     val unselectedIconRes: DrawableResource,
    val route: NavKey,
) {
    Movies(
        Res.string.movies,
        Res.drawable.film_solid,
        Res.drawable.film_outline,
        MoviesDestination
    ),
    Series(
        Res.string.series,
        Res.drawable.tv_solid,
        Res.drawable.tv_outline,
        SeriesDestination
    ),
    Actors(
        Res.string.actors,
        Res.drawable.check_badge_solid,
        Res.drawable.check_badge_outline,
        ActorsDestination
    ),
    Lists(
        Res.string.watch_lists,
        Res.drawable.bookmark_solid,
        Res.drawable.bookmark_outline,
        WatchListDestination
    ),
    Settings(
        Res.string.settings,
        Res.drawable.cog_solid,
        Res.drawable.cog_outline,
        SettingsDestination
    );

    val loginRequired: Boolean
        get() = this in listOf(Lists)
}