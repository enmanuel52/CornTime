package com.enmanuelbergling.core.remote

import android.content.Context
import kotlinx.coroutines.flow.Flow
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.util.Locale

actual val systemModule: Module
    get() = module {
        singleOf(::System)
    }

actual class System(private val context: Context) {
    actual fun isNetworkAvailable(): Flow<Boolean> = context.isOnline
}

actual fun getSystemLanguage(): String = Locale.getDefault().toLanguageTag()