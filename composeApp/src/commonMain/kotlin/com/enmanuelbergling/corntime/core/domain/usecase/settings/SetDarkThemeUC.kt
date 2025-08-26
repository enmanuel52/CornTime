package com.enmanuelbergling.corntime.core.domain.usecase.settings

import com.enmanuelbergling.corntime.core.domain.datasource.preferences.SettingsPreferencesDS
import com.enmanuelbergling.corntime.core.model.settings.DarkTheme

class SetDarkThemeUC(private val localDS: SettingsPreferencesDS) {
    operator fun invoke(theme: DarkTheme) = localDS.setDarkTheme(theme)
}