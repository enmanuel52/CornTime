package com.enmanuelbergling.corntime.core.domain.usecase.onboarding

import com.enmanuelbergling.corntime.core.domain.datasource.preferences.OnboardingPreferenceDS

class IsOnboardingUC(
    private val preferenceDS: OnboardingPreferenceDS,
) {
    operator fun invoke() = preferenceDS.isOnboarding()
}