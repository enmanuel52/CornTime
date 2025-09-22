package com.enmanuelbergling.corntime.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * Initializes Koin with the provided configuration and modules.
 *
 * @param config A lambda that allows further configuration of the Koin application.
 *              It defaults to an empty configuration.
 */
fun initKoin(config: KoinAppDeclaration = {}) = startKoin {
    config(this)
    modules(appModule)
}