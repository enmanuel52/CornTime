package com.enmanuelbergling.corntime.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.navOptions
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.savedstate.serialization.SavedStateConfiguration
import com.enmanuelbergling.corntime.ui.CornTimeAppState
import com.enmanuelbergling.feature.actor.navigation.actorsGraph
import com.enmanuelbergling.feature.actor.navigation.navigateToActorsDetails
import com.enmanuelbergling.feature.auth.navigation.loginScreen
import com.enmanuelbergling.feature.auth.navigation.navigateToLoginScreen
import com.enmanuelbergling.feature.movies.navigation.MovieSearchDestination
import com.enmanuelbergling.feature.movies.navigation.MoviesDestination
import com.enmanuelbergling.feature.movies.navigation.MoviesDetailsDestination
import com.enmanuelbergling.feature.movies.navigation.MoviesFilterDestination
import com.enmanuelbergling.feature.movies.navigation.MoviesGraphDestination
import com.enmanuelbergling.feature.movies.navigation.MoviesSectionDestination
import com.enmanuelbergling.feature.movies.navigation.moviesGraph
import com.enmanuelbergling.feature.movies.navigation.navigateToMovieFilter
import com.enmanuelbergling.feature.movies.navigation.navigateToMovieSearch
import com.enmanuelbergling.feature.movies.navigation.navigateToMoviesDetails
import com.enmanuelbergling.feature.movies.navigation.navigateToMoviesSection
import com.enmanuelbergling.feature.series.navigation.seriesGraph
import com.enmanuelbergling.feature.settings.navigation.settingsGraph
import com.enmanuelbergling.feature.watchlists.navigation.listGraph
import com.enmanuelbergling.feature.watchlists.navigation.navigateToListDetailsScreen
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic


@Composable
fun CtiNavHost(
    state: CornTimeAppState,
    modifier: Modifier = Modifier,
    onOpenDrawer: () -> Unit,
) {
    val navController = state.navController

    val moviesBackStack = rememberNavBackStack(
        configuration = configuration,
        MoviesDestination,
    )

    NavHost(
        navController = navController,
        startDestination = state.startDestination,
        modifier = modifier,
    ) {

        moviesGraph(
            moviesBackStack,
            onBack = {
                if (moviesBackStack.size > 1) moviesBackStack.removeLastOrNull()

                if (moviesBackStack.size == 1) {
                    navController.popBackStack()
                    moviesBackStack[0] = MoviesDestination
                }
            },
            onMovie = moviesBackStack::navigateToMoviesDetails,
            onActor = { action ->
                navController.navigateToActorsDetails(
                    action.id, action.imageUrl, action.name
                )
            },
            onMore = moviesBackStack::navigateToMoviesSection,
            onSearch = moviesBackStack::navigateToMovieSearch,
            onFilter = moviesBackStack::navigateToMovieFilter,
            onOpenDrawer = onOpenDrawer,
        )

        seriesGraph(onOpenDrawer = onOpenDrawer)

        actorsGraph(
            onBack = navController::navigateUp,
            onDetails = { action ->
                navController.navigateToActorsDetails(
                    action.id, action.imageUrl, action.name
                )
            },
            onMovie = {
                moviesBackStack.clear()
                moviesBackStack.add(MoviesDetailsDestination(it))
                navController.navigate(MoviesGraphDestination)
            },
            onOpenDrawer = onOpenDrawer,
        )

        loginScreen(
            onLoginSucceed = {
                navController.navigate(
                    MoviesGraphDestination,
                    navOptions = navOptions {
                        popUpTo(MoviesGraphDestination) { inclusive = true }
                    }
                )
            },
            onBack = navController::navigateUp
        )

        listGraph(
            onDetails = navController::navigateToListDetailsScreen,
            onMovieDetails = {
                moviesBackStack.clear()
                moviesBackStack.add(MoviesDetailsDestination(it))
                navController.navigate(MoviesGraphDestination)
            },
            onBack = navController::navigateUp,
            onOpenDrawer = onOpenDrawer,
        )

        settingsGraph(
            onBack = {
                state.navigateToDrawerDestination(TopDestination.Movies)
            },
            onLogin = navController::navigateToLoginScreen
        )
    }
}

private val configuration = SavedStateConfiguration {
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
        }
    }
}