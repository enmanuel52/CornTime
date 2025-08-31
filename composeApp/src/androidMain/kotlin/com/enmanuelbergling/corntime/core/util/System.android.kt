package com.enmanuelbergling.corntime.core.util

import android.content.Context
import android.content.res.Resources
import androidx.core.os.ConfigurationCompat
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

actual class System(private val context: Context) {
    actual fun isNetworkAvailable() = context.isOnline
}

actual fun getSystemLanguage(): String {
    val config = Resources.getSystem().configuration
    return ConfigurationCompat.getLocales(config)[0].toString().replace('_', '-')
}

actual val systemModule: Module
    get() = module {
        singleOf(::System)
    }