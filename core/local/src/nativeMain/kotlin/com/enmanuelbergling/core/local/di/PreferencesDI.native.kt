package com.enmanuelbergling.core.local.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.enmanuelbergling.core.local.PREFERENCES_FILENAME
import com.enmanuelbergling.core.local.createPreferencesDataStore
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

actual val platformPreferencesModule: Module
    get() = module {
        single { createDataStore() }
    }

@OptIn(ExperimentalForeignApi::class)
private fun createDataStore(): DataStore<Preferences> = createPreferencesDataStore(
    producePath = {
        val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
        requireNotNull(documentDirectory).path + "/$PREFERENCES_FILENAME"
    }
)