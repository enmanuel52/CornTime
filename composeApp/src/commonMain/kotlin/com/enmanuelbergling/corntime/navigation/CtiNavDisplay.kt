package com.enmanuelbergling.corntime.navigation

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.enmanuelbergling.corntime.ui.CornTimeAppState
import com.enmanuelbergling.feature.actor.navigation.actorsGraph
import com.enmanuelbergling.feature.actor.navigation.navigateToActorsDetails
import com.enmanuelbergling.feature.auth.navigation.loginScreen
import com.enmanuelbergling.feature.auth.navigation.navigateToLoginScreen
import com.enmanuelbergling.feature.movies.navigation.MoviesDestination
import com.enmanuelbergling.feature.movies.navigation.moviesGraph
import com.enmanuelbergling.feature.movies.navigation.navigateToMovieFilter
import com.enmanuelbergling.feature.movies.navigation.navigateToMovieSearch
import com.enmanuelbergling.feature.movies.navigation.navigateToMoviesDetails
import com.enmanuelbergling.feature.movies.navigation.navigateToMoviesSection
import com.enmanuelbergling.feature.movies.navigation.navigateToRecommendedMovies
import com.enmanuelbergling.feature.series.navigation.seriesGraph
import com.enmanuelbergling.feature.settings.navigation.settingsGraph
import com.enmanuelbergling.feature.watchlists.navigation.listGraph
import com.enmanuelbergling.feature.watchlists.navigation.navigateToListDetailsScreen


@Composable
fun CtiNavDisplay(
    state: CornTimeAppState,
    modifier: Modifier = Modifier,
    onOpenDrawer: () -> Unit,
) {
    val backStack = state.navBackStack

    val onBack = remember {
        { if (backStack.size > 1) backStack.removeLastOrNull() }
    }

    NavDisplay(
        backStack = backStack,
        onBack = onBack,
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        modifier = modifier,
        transitionSpec = {
            // Slide in from right when navigating forward
            slideInHorizontally { it } togetherWith slideOutHorizontally { -it }
        },
        popTransitionSpec = {
            // Slide in from left when navigating back
            slideInHorizontally { -it } togetherWith slideOutHorizontally { it }
        },
        predictivePopTransitionSpec = {
            // Slide in from left when navigating back
            slideInHorizontally{ -it } togetherWith slideOutHorizontally { it }
        },
        entryProvider = entryProvider<Any> {

            moviesGraph(
                onBack = onBack,
                onMovie = backStack::navigateToMoviesDetails,
                onRecommendedMovies = backStack::navigateToRecommendedMovies,
                onActor = { action ->
                    backStack.navigateToActorsDetails(
                        action.id, action.imageUrl, action.name
                    )
                },
                onMore = backStack::navigateToMoviesSection,
                onSearch = backStack::navigateToMovieSearch,
                onFilter = backStack::navigateToMovieFilter,
                onOpenDrawer = onOpenDrawer,
            )

            seriesGraph(onOpenDrawer = onOpenDrawer)

            actorsGraph(
                onBack = onBack,
                onDetails = { action ->
                    backStack.navigateToActorsDetails(
                        action.id, action.imageUrl, action.name
                    )
                },
                onMovie = backStack::navigateToMoviesDetails,
                onOpenDrawer = onOpenDrawer,
            )

            loginScreen(
                onLoginSucceed = {
                    backStack.clear()
                    backStack.add(MoviesDestination)
                },
                onBack = onBack
            )

            listGraph(
                onDetails = backStack::navigateToListDetailsScreen,
                onMovieDetails = backStack::navigateToMoviesDetails,
                onBack = onBack,
                onOpenDrawer = onOpenDrawer,
            )

            settingsGraph(
                onBack = onBack,
                onLogin = backStack::navigateToLoginScreen
            )
        }
    )
}