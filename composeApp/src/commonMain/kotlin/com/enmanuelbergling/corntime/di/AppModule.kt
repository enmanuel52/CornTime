package com.enmanuelbergling.corntime.di

import com.enmanuelbergling.corntime.core.data.local.di.preferencesModule
import com.enmanuelbergling.corntime.core.data.remote.di.remoteDsModule
import com.enmanuelbergling.corntime.core.data.remote.di.remoteModule
import com.enmanuelbergling.core.domain.usecase.di.ucModule
import com.enmanuelbergling.corntime.core.util.systemModule
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