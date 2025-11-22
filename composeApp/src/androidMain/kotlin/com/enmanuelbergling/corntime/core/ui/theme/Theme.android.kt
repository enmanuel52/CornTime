package com.enmanuelbergling.corntime.core.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.enmanuelbergling.core.model.settings.DarkTheme

@Composable
actual fun getColorScheme(
    darkTheme: DarkTheme,
    dynamicColor: Boolean
): ColorScheme = when {
    dynamicColor && (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) -> {
        val context = LocalContext.current
        if (darkTheme == DarkTheme.Yes) dynamicDarkColorScheme(context)
        else if (darkTheme == DarkTheme.System && isSystemInDarkTheme())
            dynamicDarkColorScheme(context)
        else dynamicLightColorScheme(context)
    }

    darkTheme == DarkTheme.No -> lightScheme
    darkTheme == DarkTheme.Yes -> darkScheme
    else -> if (isSystemInDarkTheme()) darkScheme else lightScheme
}