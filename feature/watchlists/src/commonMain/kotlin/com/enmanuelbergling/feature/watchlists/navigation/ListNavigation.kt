package com.enmanuelbergling.feature.watchlists.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.enmanuelbergling.core.ui.navigation.topEntry
import com.enmanuelbergling.feature.watchlists.details.WatchListDetailsRoute
import com.enmanuelbergling.feature.watchlists.home.WatchListRoute
import kotlinx.serialization.Serializable


@Serializable
data object WatchListDestination : NavKey

@Serializable
data class ListDetailsDestination(
    val listId: Int,
    val listName: String,
) : NavKey

fun NavBackStack<NavKey>.navigateToListDetailsScreen(
    listId: Int,
    listName: String,
) {
    add(ListDetailsDestination(listId, listName))
}

fun EntryProviderScope<Any>.listGraph(
    onDetails: (listId: Int, listName: String) -> Unit,
    onMovieDetails: (movieId: Int) -> Unit,
    onBack: () -> Unit,
    onOpenDrawer: () -> Unit,
) {
    topEntry<WatchListDestination> {
        WatchListRoute(
            onDetails = onDetails,
            onOpenDrawer = onOpenDrawer,
        )
    }

    entry<ListDetailsDestination> { entry ->
        val (listId, listName) = entry

        WatchListDetailsRoute(
            listId = listId,
            listName = listName,
            onMovieDetails = onMovieDetails,
            onBack = onBack
        )
    }
}