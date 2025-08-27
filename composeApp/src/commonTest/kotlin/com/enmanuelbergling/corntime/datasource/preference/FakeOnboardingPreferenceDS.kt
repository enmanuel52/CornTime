package com.enmanuelbergling.corntime.datasource.preference

import com.enmanuelbergling.corntime.core.domain.datasource.preferences.OnboardingPreferenceDS
import kotlinx.coroutines.flow.flow

class FakeOnboardingPreferenceDS: OnboardingPreferenceDS {
    override fun isOnboarding() = flow { emit(false) }

    override suspend fun finishOnboarding() =Unit
}