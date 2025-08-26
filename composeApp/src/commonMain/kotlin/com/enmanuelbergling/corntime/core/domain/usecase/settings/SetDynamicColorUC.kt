package com.enmanuelbergling.corntime.core.domain.usecase.settings

import com.enmanuelbergling.corntime.core.domain.datasource.preferences.SettingsPreferencesDS

class SetDynamicColorUC(
    private val preferencesDS: SettingsPreferencesDS,
) {
    operator fun invoke(active: Boolean) {
        preferencesDS.setDynamicColor(active)
    }
}