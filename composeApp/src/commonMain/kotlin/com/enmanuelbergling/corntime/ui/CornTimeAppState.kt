package com.enmanuelbergling.corntime.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.enmanuelbergling.feature.movies.navigation.MoviesDestination
import com.enmanuelbergling.feature.movies.navigation.navigateToMoviesGraph
import com.enmanuelbergling.feature.series.navigation.navigateToSeriesGraph
import com.enmanuelbergling.feature.settings.navigation.navigateToSettingsGraph
import com.enmanuelbergling.feature.watchlists.navigation.navigateToListGraph
import com.enmanuelbergling.corntime.navigation.TopDestination
import com.enmanuelbergling.feature.actor.navigation.navigateToActorsGraph

@Composable
fun rememberCornTimeAppState(
    isOnline: Boolean = true,
    navController: NavHostController = rememberNavController(),
) = remember(navController) { CornTimeAppState(isOnline, navController) }


@Stable
class CornTimeAppState(
    val isOnline: Boolean = true,
    val navController: NavHostController,
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val startDestination =
        _root_ide_package_.com.enmanuelbergling.feature.movies.navigation.MoviesGraphDestination

    val isTopDestination: Boolean
        @Composable get() = currentDestination?.let { destination ->
            TopDestination.entries.mapNotNull { it.route }
                .any { route -> destination.hasRoute(route::class) }
        } ?: false

    val mainDrawerEnabled: Boolean
        @Composable get() = currentDestination?.let { destination ->
            listOf(TopDestination.Movies, TopDestination.Series, TopDestination.Actors)
                .mapNotNull { it.route }
                .any { route -> destination.hasRoute(route::class) }
        } ?: false

    @Composable
    fun matchRoute(route: Any) = currentDestination?.hasRoute(route::class) == true

    fun navigateToDrawerDestination(destination: TopDestination) {
        when (destination) {
            TopDestination.Movies -> navController.navigateToMoviesGraph(
                navOptions {
                    popUpTo<com.enmanuelbergling.feature.movies.navigation.MoviesDestination> {
                        inclusive = true
                    }
                }
            )


            TopDestination.Series -> navController.navigateToSeriesGraph(
                navOptions {
                    popUpTo<MoviesDestination>()
                }
            )


            TopDestination.Actors -> navController.navigateToActorsGraph(
                navOptions {
                    popUpTo<com.enmanuelbergling.feature.movies.navigation.MoviesDestination>()
                }
            )

            TopDestination.Lists -> navController.navigateToListGraph(
                navOptions {
                    popUpTo<MoviesDestination>()
                }
            )

            TopDestination.Settings -> navController.navigateToSettingsGraph(
                navOptions {
                    popUpTo<MoviesDestination>()
                }
            )

        }
    }
}