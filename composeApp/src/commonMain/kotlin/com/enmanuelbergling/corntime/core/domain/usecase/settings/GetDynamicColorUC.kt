package com.enmanuelbergling.corntime.core.domain.usecase.settings

import com.enmanuelbergling.corntime.core.domain.datasource.preferences.SettingsPreferencesDS

class GetDynamicColorUC(
    private val preferencesDS: SettingsPreferencesDS
) {
     operator fun invoke()=preferencesDS.getDynamicColor()
}