package com.enmanuelbergling.feature.actor.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.LocalNavAnimatedContentScope
import com.enmanuelbergling.core.ui.navigation.ActorDetailNavAction
import com.enmanuelbergling.core.ui.navigation.topEntry
import com.enmanuelbergling.feature.actor.details.ActorDetailsRoute
import com.enmanuelbergling.feature.actor.home.ActorsScreen
import kotlinx.serialization.Serializable


@Serializable
data object ActorsDestination : NavKey

@Serializable
data class ActorDetailsDestination(
    val id: Int,
    val imageUrl: String?,
    val name: String,
) : NavKey {
    init {
        require(imageUrl == null || imageUrl.isNotBlank()) { "actor image url must not be blank" }
    }
}

fun NavBackStack<NavKey>.navigateToActorsDetails(
    id: Int,
    imageUrl: String?,
    name: String,
) {
    add(ActorDetailsDestination(id, imageUrl, name))
}

fun EntryProviderScope<Any>.actorsGraph(
    onBack: () -> Unit,
    onDetails: (ActorDetailNavAction) -> Unit,
    onMovie: (movieId: Int) -> Unit,
    onOpenDrawer: () -> Unit,
) {
    topEntry<ActorsDestination> {
        LocalNavAnimatedContentScope.current.ActorsScreen(
            onDetails = onDetails, onOpenDrawer
        )
    }

    entry<ActorDetailsDestination> { entry ->
        LocalNavAnimatedContentScope.current.ActorDetailsRoute(
            id = entry.id,
            imagePath = entry.imageUrl,
            name = entry.name,
            onMovie = onMovie,
            onBack = onBack
        )
    }
}