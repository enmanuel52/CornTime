package com.enmanuelbergling.corntime.di

import com.enmanuelbergling.corntime.datasource.preference.FakeAuthPreferenceDS
import com.enmanuelbergling.corntime.datasource.preference.FakeOnboardingPreferenceDS
import com.enmanuelbergling.corntime.datasource.preference.FakeSettingsPreferencesDS
import com.enmanuelbergling.corntime.datasource.preference.FakeUserPreferencesDS
import com.enmanuelbergling.corntime.datasource.remote.FakeActorRemoteDS
import com.enmanuelbergling.corntime.datasource.remote.FakeAuthRemoteDS
import com.enmanuelbergling.corntime.datasource.remote.FakeMovieRemoteDS
import com.enmanuelbergling.corntime.datasource.remote.FakeUserRemoteDS
import com.enmanuelbergling.core.domain.datasource.preferences.AuthPreferenceDS
import com.enmanuelbergling.core.domain.datasource.preferences.OnboardingPreferenceDS
import com.enmanuelbergling.core.domain.datasource.preferences.SettingsPreferencesDS
import com.enmanuelbergling.core.domain.datasource.preferences.UserPreferenceDS
import com.enmanuelbergling.core.domain.datasource.remote.ActorRemoteDS
import com.enmanuelbergling.core.domain.datasource.remote.AuthRemoteDS
import com.enmanuelbergling.core.domain.datasource.remote.MovieRemoteDS
import com.enmanuelbergling.core.domain.datasource.remote.UserRemoteDS
import org.koin.dsl.module

val testingPreferenceModule = module {
    single<SettingsPreferencesDS> { FakeSettingsPreferencesDS() }

    single<AuthPreferenceDS> { FakeAuthPreferenceDS() }

    single<UserPreferenceDS> { FakeUserPreferencesDS() }

    single<OnboardingPreferenceDS> { FakeOnboardingPreferenceDS() }
}

val testingRemoteModule = module {
    single<MovieRemoteDS> { FakeMovieRemoteDS() }

    single<ActorRemoteDS> { FakeActorRemoteDS() }

    single<AuthRemoteDS> { FakeAuthRemoteDS() }

    single<UserRemoteDS> { FakeUserRemoteDS() }
}

val testingDataSourceModule = listOf(testingRemoteModule, testingPreferenceModule)