package com.enmanuelbergling.corntime.di

import com.enmanuelbergling.core.domain.usecase.di.ucModule
import com.enmanuelbergling.core.local.di.preferencesModule
import com.enmanuelbergling.core.remote.di.remoteDsModule
import com.enmanuelbergling.core.remote.di.remoteModule
import com.enmanuelbergling.core.remote.systemModule
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

val appModule = module {
    val modules =
        ucModule + remoteModule + remoteDsModule + featuresModule +
                preferencesModule + systemModule

    loadKoinModules(
        modules = modules,
    )
}