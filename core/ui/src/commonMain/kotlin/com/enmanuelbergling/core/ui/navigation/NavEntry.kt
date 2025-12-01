package com.enmanuelbergling.core.ui.navigation

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.ui.NavDisplay

/**
 * Add an entry provider to the [EntryProviderScope]
 * Wrapper for top destinations
 * */
context(scope: EntryProviderScope<Any>)
inline fun <reified K : Any> topEntry(
    metadata: Map<String, Any> = emptyMap(),
    noinline content: @Composable (K) -> Unit,
) {
    scope.entry<K>(metadata = metadata + NavMetadata) { entry: K ->
        content(entry)
    }
}

val NavMetadata = NavDisplay.transitionSpec {
    fadeIn() togetherWith fadeOut()
} + NavDisplay.popTransitionSpec {
    fadeIn() togetherWith fadeOut()
} + NavDisplay.predictivePopTransitionSpec {
    fadeIn() togetherWith fadeOut()
}