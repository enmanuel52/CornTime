package com.enmanuelbergling.corntime.feature.settings.di

import com.enmanuelbergling.corntime.feature.settings.home.SettingsVM
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val settingsModule = module {
    viewModelOf(::SettingsVM)
}
