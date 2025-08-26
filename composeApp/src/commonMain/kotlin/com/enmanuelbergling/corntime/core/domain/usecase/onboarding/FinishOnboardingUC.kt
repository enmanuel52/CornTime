package com.enmanuelbergling.corntime.core.domain.usecase.onboarding

import com.enmanuelbergling.corntime.core.domain.datasource.preferences.OnboardingPreferenceDS

class FinishOnboardingUC(
    private val preferenceDS: OnboardingPreferenceDS,
) {
    suspend operator fun invoke() = preferenceDS.finishOnboarding()
}