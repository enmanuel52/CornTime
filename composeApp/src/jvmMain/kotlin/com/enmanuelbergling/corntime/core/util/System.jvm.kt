package com.enmanuelbergling.corntime.core.util

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.koin.core.module.Module
import org.koin.dsl.module
import java.net.InetSocketAddress
import java.net.Socket
import java.util.Locale

actual class System {
    actual fun isNetworkAvailable(): Flow<Boolean> {
        // For JVM, this is a basic check.
        // It doesn't actively monitor network changes.
        // It checks connectivity once when the Flow is collected.
        return flowOf(checkJvmNetworkAvailability())
    }

    private fun checkJvmNetworkAvailability(): Boolean {
        return try {
            // Try to connect to a reliable host (e.g., Google's DNS server)
            // Timeout is set to 1.5 seconds
            Socket().use { socket ->
                socket.connect(InetSocketAddress("8.8.8.8", 53), 1500)
                true
            }
        } catch (e: Exception) {
            // If connection fails, assume network is unavailable
            false
        }
    }
}

actual fun getSystemLanguage(): String {
    return Locale.getDefault().toLanguageTag()
}

actual val systemModule: Module
    get() = module {
        single { System() }
    }