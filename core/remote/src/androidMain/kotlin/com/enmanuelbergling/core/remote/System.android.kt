package com.enmanuelbergling.core.remote

import kotlinx.coroutines.flow.Flow
import org.koin.core.module.Module

actual val systemModule: Module
    get() = TODO("Not yet implemented")

actual class System {
    actual fun isNetworkAvailable(): Flow<Boolean> {
        TODO("Not yet implemented")
    }
}

actual fun getSystemLanguage(): String {
    TODO("Not yet implemented")
}