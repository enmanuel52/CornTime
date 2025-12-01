package com.enmanuelbergling.corntime.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.enmanuelbergling.corntime.navigation.TopDestination
import com.enmanuelbergling.feature.movies.navigation.MoviesDestination

@Composable
fun rememberCornTimeAppState(
    isOnline: Boolean = true,
    navBackStack: NavBackStack<NavKey>,
) = remember(navBackStack) { CornTimeAppState(isOnline, navBackStack) }


@Stable
class CornTimeAppState(
    val isOnline: Boolean = true,
    val navBackStack: NavBackStack<NavKey>,
) {
    val isTopDestination: Boolean
        get() = TopDestination.entries.map { it.route }
            .any { route -> navBackStack.last() == route }

    val mainDrawerEnabled: Boolean
        get() = listOf(
            TopDestination.Movies,
            TopDestination.Series,
            TopDestination.Actors
        )
            .map { it.route }
            .any { route -> navBackStack.last() == route }

    fun navigateToDrawerDestination(destination: TopDestination) {
        if (destination.route == navBackStack.last()) return

        if (destination == TopDestination.Movies) {
            navBackStack.removeLastOrNull()
        } else {
            navBackStack.clear()
            navBackStack.add(MoviesDestination)
            navBackStack.add(destination.route)
        }
    }
}