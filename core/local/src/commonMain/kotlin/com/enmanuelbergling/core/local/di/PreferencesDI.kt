package com.enmanuelbergling.core.local.di

import com.enmanuelbergling.core.local.AuthPreferenceDSImpl
import com.enmanuelbergling.core.local.OnboardingPreferenceDSImpl
import com.enmanuelbergling.core.local.SearchSuggestionDSImpl
import com.enmanuelbergling.core.local.SettingsPreferencesDSImpl
import com.enmanuelbergling.core.local.UserPreferenceDSImpl
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