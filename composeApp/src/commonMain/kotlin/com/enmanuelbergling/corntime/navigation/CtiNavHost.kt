package com.enmanuelbergling.corntime.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.enmanuelbergling.corntime.feature.movies.navigation.moviesGraph
import com.enmanuelbergling.corntime.feature.movies.navigation.moviesFilterScreen
import com.enmanuelbergling.corntime.feature.movies.navigation.movieSearchScreen
import com.enmanuelbergling.corntime.feature.movies.navigation.navigateToMovieFilter
import com.enmanuelbergling.corntime.feature.movies.navigation.navigateToMovieSearch
import com.enmanuelbergling.corntime.feature.movies.navigation.navigateToMoviesDetails
import com.enmanuelbergling.corntime.feature.movies.navigation.navigateToMoviesSection
import com.enmanuelbergling.corntime.ui.CornTimeAppState


@Composable
fun CtiNavHost(
    state: CornTimeAppState,
    modifier: Modifier = Modifier,
    onOpenDrawer: () -> Unit,
) {
    val navController = state.navController


    NavHost(
        navController,
        startDestination = state.startDestination,
        modifier = modifier,
        ) {

        moviesGraph(
            onBack = navController::navigateUp,
            onMovie = navController::navigateToMoviesDetails,
            onActor = { action ->
//                navController.navigateToActorsDetails(
//                    action.id, action.imageUrl, action.name
//                )
            },
            onMore = navController::navigateToMoviesSection,
            onSearch = state.navController::navigateToMovieSearch,
            onFilter = state.navController::navigateToMovieFilter,
            onOpenDrawer = onOpenDrawer,
        )

        movieSearchScreen(navController::navigateToMoviesDetails, navController::navigateUp)

        moviesFilterScreen(navController::navigateToMoviesDetails, navController::navigateUp)

        /*seriesGraph(onOpenDrawer = onOpenDrawer)

        actorsGraph(
            onBack = navController::navigateUp,
            onDetails = { action ->
                navController.navigateToActorsDetails(
                    action.id, action.imageUrl, action.name
                )
            },
            onMovie = navController::navigateToMoviesDetails,
            onOpenDrawer = onOpenDrawer,
        )

        loginScreen(
            onLoginSucceed = {
                navController.navigateToMoviesGraph(
                    navOptions {
                        popUpTo(MoviesGraphDestination) {
                            inclusive = true
                        }
                    }
                )
            },
            onBack = navController::navigateUp
        )

        listGraph(
            onDetails = navController::navigateToListDetailsScreen,
            onMovieDetails = navController::navigateToMoviesDetails,
            onAddShortcut = { watchlist -> state.addWatchlistShortcut(context, watchlist) },
            onDeleteShortcut = { watchlistId ->
                state.deleteWatchlistShortcut(
                    context = context,
                    watchlistId = watchlistId
                )
            },
            onBack = navController::navigateUp,
            onOpenDrawer = onOpenDrawer,
        )

        settingsGraph(
            onBack = {
                state.navigateToDrawerDestination(TopDestination.Movies)
            },
            onLogin = navController::navigateToLoginScreen
        )*/
    }
}