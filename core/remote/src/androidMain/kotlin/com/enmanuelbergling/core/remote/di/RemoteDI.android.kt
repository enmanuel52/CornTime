package com.enmanuelbergling.core.remote.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.binds
import org.koin.dsl.module

actual val platformRemoteModule: Module
    get() = module {
        single{ OkHttp.create() }.binds(arrayOf(HttpClientEngine::class))
    }