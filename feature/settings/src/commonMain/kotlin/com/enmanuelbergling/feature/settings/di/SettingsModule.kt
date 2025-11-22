package com.enmanuelbergling.feature.settings.di

import com.enmanuelbergling.feature.settings.home.SettingsVM
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module


val settingsModule = module {
    viewModelOf(::SettingsVM)
}
