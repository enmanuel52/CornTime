package com.enmanuelbergling.corntime.core.domain.usecase.settings

import com.enmanuelbergling.corntime.core.domain.datasource.preferences.SettingsPreferencesDS

class GetDarkThemeUC(private val localDS: SettingsPreferencesDS) {
    operator fun invoke() = localDS.getDarkTheme()
}