package com.enmanuelbergling.corntime.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import com.enmanuelbergling.core.domain.datasource.preferences.OnboardingPreferenceDS
import kotlinx.coroutines.flow.map

class OnboardingPreferenceDSImpl(private val dataStore: DataStore<Preferences>) :
    OnboardingPreferenceDS {

    private object Keys {
        val ONBOARDING = booleanPreferencesKey("onboarding")
    }

    override fun isOnboarding() = dataStore.data.map { it[Keys.ONBOARDING] ?: true }

    override suspend fun finishOnboarding() {
        dataStore.edit {
            it[Keys.ONBOARDING] = false
        }
    }
}