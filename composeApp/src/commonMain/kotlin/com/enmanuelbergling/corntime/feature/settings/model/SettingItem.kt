package com.enmanuelbergling.corntime.feature.settings.model

import corntime.composeapp.generated.resources.Res
import corntime.composeapp.generated.resources.dark_mode
import corntime.composeapp.generated.resources.dynamic_color
import corntime.composeapp.generated.resources.moon
import corntime.composeapp.generated.resources.paint_brush
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource

internal data class SettingItem(
  val iconRes: DrawableResource,
    val label: StringResource,
) {
    companion object {
        val DarkMode = SettingItem(
            Res.drawable.moon,
            label = Res.string.dark_mode
        )

        val DynamicColor = SettingItem(
            iconRes = Res.drawable.paint_brush,
            label = Res.string.dynamic_color
        )
    }
}
