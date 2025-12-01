package com.enmanuelbergling.feature.series.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.enmanuelbergling.core.ui.navigation.topEntry
import com.enmanuelbergling.feature.series.home.SeriesScreen
import kotlinx.serialization.Serializable


@Serializable
data object SeriesDestination : NavKey

fun EntryProviderScope<Any>.seriesGraph(
    onOpenDrawer: () -> Unit,
) {
    topEntry<SeriesDestination> {
        SeriesScreen(onOpenDrawer)
    }
}