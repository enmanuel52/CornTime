package com.enmanuelbergling.corntime.core.data.local.di

import com.enmanuelbergling.corntime.core.data.local.AuthPreferenceDSImpl
import com.enmanuelbergling.corntime.core.data.local.OnboardingPreferenceDSImpl
import com.enmanuelbergling.corntime.core.data.local.SearchSuggestionDSImpl
import com.enmanuelbergling.corntime.core.data.local.SettingsPreferencesDSImpl
import com.enmanuelbergling.corntime.core.data.local.UserPreferenceDSImpl
import com.enmanuelbergling.core.domain.datasource.preferences.AuthPreferenceDS
import com.enmanuelbergling.core.domain.datasource.preferences.OnboardingPreferenceDS
import com.enmanuelbergling.core.domain.datasource.preferences.SearchSuggestionDS
import com.enmanuelbergling.core.domain.datasource.preferences.SettingsPreferencesDS
import com.enmanuelbergling.core.domain.datasource.preferences.UserPreferenceDS
import org.koin.core.module.Module
import org.koin.dsl.module

/**
 * DataStore<Preferences>
 * */
expect val platformPreferencesModule: Module

val preferencesModule = module {
    includes(platformPreferencesModule)

    single<SettingsPreferencesDS> { SettingsPreferencesDSImpl(get()) }

    single<AuthPreferenceDS> { AuthPreferenceDSImpl(get()) }

    single<UserPreferenceDS> { UserPreferenceDSImpl(get()) }

    single<SearchSuggestionDS> { SearchSuggestionDSImpl(get()) }

    single<OnboardingPreferenceDS> { OnboardingPreferenceDSImpl(get()) }
}