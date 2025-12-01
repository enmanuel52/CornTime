package com.enmanuelbergling.feature.movies.navigation

import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import androidx.navigation3.ui.NavDisplay
import com.enmanuelbergling.core.model.MovieSection
import com.enmanuelbergling.core.ui.navigation.ActorDetailNavAction
import com.enmanuelbergling.core.ui.navigation.topEntry
import com.enmanuelbergling.feature.movies.details.MovieDetailsScreen
import com.enmanuelbergling.feature.movies.filter.MoviesFilterRoute
import com.enmanuelbergling.feature.movies.home.MoviesScreen
import com.enmanuelbergling.feature.movies.list.NowPlayingMoviesScreen
import com.enmanuelbergling.feature.movies.list.PopularMoviesScreen
import com.enmanuelbergling.feature.movies.list.TopRatedMoviesScreen
import com.enmanuelbergling.feature.movies.list.UpcomingMoviesScreen
import com.enmanuelbergling.feature.movies.search.MovieSearchScreen
import kotlinx.serialization.Serializable


@Serializable
data object MoviesDestination : NavKey

@Serializable
data class MoviesDetailsDestination(val id: Int) : NavKey

@Serializable
data class MoviesSectionDestination(val section: String) : NavKey

@Serializable
data object MoviesFilterDestination : NavKey

@Serializable
data object MovieSearchDestination : NavKey

fun NavBackStack<NavKey>.navigateToMoviesDetails(id: Int) {
    add(MoviesDetailsDestination(id))
}

fun NavBackStack<NavKey>.navigateToMoviesSection(
    movieSection: MovieSection,
) {
    add(MoviesSectionDestination("$movieSection"))
}

fun NavBackStack<NavKey>.navigateToMovieFilter() {
    add(MoviesFilterDestination)
}


fun NavBackStack<NavKey>.navigateToMovieSearch() {
    add(MovieSearchDestination)
}

fun EntryProviderScope<Any>.moviesGraph(
    onBack: () -> Unit,
    onMovie: (id: Int) -> Unit,
    onActor: (ActorDetailNavAction) -> Unit,
    onMore: (MovieSection) -> Unit,
    onSearch: () -> Unit,
    onFilter: () -> Unit,
    onOpenDrawer: () -> Unit,
) {
    topEntry<MoviesDestination> {
        MoviesScreen(
            onDetails = onMovie,
            onMore = onMore,
            onSearch = onSearch,
            onFilter = onFilter,
            onOpenDrawer = onOpenDrawer
        )
    }

    entry<MoviesDetailsDestination> { entry ->
        LocalNavAnimatedContentScope.current.MovieDetailsScreen(id = entry.id, onActor, onBack)
    }
    entry<MoviesSectionDestination> { entry ->
        val stringSection = entry.section

        val sectionResult = runCatching { MovieSection.valueOf(stringSection) }
        sectionResult.onSuccess { result ->
            when (result) {
                MovieSection.Upcoming -> UpcomingMoviesScreen(onMovie = onMovie, onBack)
                MovieSection.NowPlaying -> NowPlayingMoviesScreen(
                    onMovie = onMovie,
                    onBack = onBack
                )

                MovieSection.TopRated -> TopRatedMoviesScreen(
                    onMovie = onMovie,
                    onBack = onBack
                )

                MovieSection.Popular -> PopularMoviesScreen(
                    onMovie = onMovie,
                    onBack = onBack
                )
            }
        }.onFailure { onBack() }
    }

    entry<MoviesFilterDestination>(
        metadata = NavDisplay.transitionSpec {
            // Slide in from top when navigating forward
            slideInVertically { -it } togetherWith slideOutVertically { +it }
        } + NavDisplay.popTransitionSpec {
            // Slide in from bottom when navigating back
            slideInVertically { it } togetherWith slideOutVertically { -it }
        } + NavDisplay.predictivePopTransitionSpec {
            // Slide in from bottom when navigating back
            slideInVertically{ it } togetherWith slideOutVertically { -it }
        }
    ) {
        MoviesFilterRoute(onBack = onBack, onMovie = onMovie)
    }

    entry<MovieSearchDestination> {
        MovieSearchScreen(onMovieDetails = onMovie, onBack)
    }
}