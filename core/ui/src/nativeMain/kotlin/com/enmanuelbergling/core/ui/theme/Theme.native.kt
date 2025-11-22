package com.enmanuelbergling.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.enmanuelbergling.core.model.settings.DarkTheme

@Composable
actual fun getColorScheme(
    darkTheme: DarkTheme,
    dynamicColor: Boolean,
)= when (darkTheme) {
    DarkTheme.No -> lightScheme
    DarkTheme.Yes -> darkScheme
    else -> if (isSystemInDarkTheme()) darkScheme else lightScheme
}