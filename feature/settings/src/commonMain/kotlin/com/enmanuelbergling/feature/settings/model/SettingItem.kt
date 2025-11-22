package com.enmanuelbergling.feature.settings.model

import corntime.feature.settings.generated.resources.Res
import corntime.feature.settings.generated.resources.dark_mode
import corntime.feature.settings.generated.resources.dynamic_color
import corntime.feature.settings.generated.resources.moon
import corntime.feature.settings.generated.resources.paint_brush
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
