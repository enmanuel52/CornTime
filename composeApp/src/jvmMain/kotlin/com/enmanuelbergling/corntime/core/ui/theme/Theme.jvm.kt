package com.enmanuelbergling.corntime.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import com.enmanuelbergling.corntime.core.model.settings.DarkTheme

@Composable
actual fun getColorScheme(
    darkTheme: DarkTheme,
    dynamicColor: Boolean
) = when{
    darkTheme == DarkTheme.No -> lightScheme
    darkTheme == DarkTheme.Yes -> darkScheme
    else -> if (isSystemInDarkTheme()) darkScheme else lightScheme
}