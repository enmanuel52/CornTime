package com.enmanuelbergling.corntime.feature.auth.di

import com.enmanuelbergling.corntime.feature.auth.LoginVM
import com.enmanuelbergling.corntime.feature.auth.model.CreateRequestTokenChainHandler
import com.enmanuelbergling.corntime.feature.auth.model.CreateSessionFromLoginChainHandler
import com.enmanuelbergling.corntime.feature.auth.model.CreateSessionIdChainHandler
import com.enmanuelbergling.corntime.feature.auth.model.GetUserDetailsChainHandler
import com.enmanuelbergling.corntime.feature.auth.model.LoginChainHandler
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

internal val loginScreenModule = module {
    singleOf(::GetUserDetailsChainHandler)
    singleOf(::CreateSessionIdChainHandler)
    singleOf(::CreateSessionFromLoginChainHandler)
    singleOf(::CreateRequestTokenChainHandler)
    singleOf(::LoginChainHandler)
}

val loginModule = module {
    viewModelOf(::LoginVM)
} + loginScreenModule