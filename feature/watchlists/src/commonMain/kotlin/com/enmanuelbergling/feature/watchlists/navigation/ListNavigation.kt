package com.enmanuelbergling.feature.watchlists.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.enmanuelbergling.core.ui.navigation.topEntry
import com.enmanuelbergling.feature.watchlists.details.WatchListDetailsRoute
import com.enmanuelbergling.feature.watchlists.home.WatchListRoute
import corntime.feature.watchlists.generated.resources.Res
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
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

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
fun EntryProviderScope<Any>.listGraph(
    onDetails: (listId: Int, listName: String) -> Unit,
    onMovieDetails: (movieId: Int) -> Unit,
    onBack: () -> Unit,
    onOpenDrawer: () -> Unit,
) {
    topEntry<WatchListDestination>(
        metadata = ListDetailSceneStrategy.listPane(
            detailPlaceholder = {
                NoWatchlistSelected()
            }
        )
    ) {
        WatchListRoute(
            onDetails = onDetails,
            onOpenDrawer = onOpenDrawer,
        )
    }

    entry<ListDetailsDestination>(
        metadata = ListDetailSceneStrategy.detailPane()
    ) { entry ->
        val (listId, listName) = entry

        WatchListDetailsRoute(
            listId = listId,
            listName = listName,
            onMovieDetails = onMovieDetails,
            onBack = onBack
        )
    }
}

@Composable
private fun NoWatchlistSelected() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("No lists selected", style = MaterialTheme.typography.titleLarge)

        val composition by rememberLottieComposition {
            LottieCompositionSpec.JsonString(
                Res.readBytes("files/empty_box.json").decodeToString()
            )
        }

        Image(
            painter = rememberLottiePainter(
                composition = composition,
                iterations = 1
            ),
            contentDescription = "Lottie animation"
        )

    }
}