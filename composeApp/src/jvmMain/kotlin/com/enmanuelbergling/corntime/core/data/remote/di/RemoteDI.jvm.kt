package com.enmanuelbergling.corntime.core.data.remote.di

import io.ktor.client.engine.HttpClientEngine
import okhttp3.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.binds
import org.koin.dsl.module

actual val platformRemoteModule: Module
    get() = module {
        single{ OkHttp }.binds(arrayOf(HttpClientEngine::class))
    }