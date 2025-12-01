package com.enmanuelbergling.feature.settings.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey
import com.enmanuelbergling.core.ui.navigation.topEntry
import com.enmanuelbergling.feature.settings.home.SettingsRoute
import kotlinx.serialization.Serializable


@Serializable
data object SettingsDestination: NavKey

fun EntryProviderScope<Any>.settingsGraph(onBack: ()->Unit, onLogin: ()->Unit) {
    topEntry<SettingsDestination> {
        SettingsRoute(onBack, onLogin)
    }
}