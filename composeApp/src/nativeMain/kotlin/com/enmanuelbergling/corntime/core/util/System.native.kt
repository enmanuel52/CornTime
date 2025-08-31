package com.enmanuelbergling.corntime.core.util

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode
import platform.NetworkExtension.NWPathStatusSatisfied
import platform.darwin.dispatch_get_main_queue

@OptIn(ExperimentalForeignApi::class)
actual class System {
    actual fun isNetworkAvailable(): Flow<Boolean> = callbackFlow {
        /*val monitor = NWPathMonitor()
        monitor.pathUpdateHandler = {
            trySend(it.status == NWPathStatusSatisfied)
        }
        monitor.start()
        monitor.setQueue(dispatch_get_main_queue()) // Run on main queue

        awaitClose { // Cleanup when the flow is cancelled
            monitor.cancel()
        }*/
        trySend(true)
    }
}

actual fun getSystemLanguage(): String {
    return NSLocale.currentLocale.languageCode
}

actual val systemModule: Module
    get() = module{
        single { System() }
    }