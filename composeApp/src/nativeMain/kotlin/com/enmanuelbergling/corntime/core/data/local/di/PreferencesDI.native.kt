package com.enmanuelbergling.corntime.core.data.local.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.enmanuelbergling.corntime.core.data.local.PREFERENCES_FILENAME
import com.enmanuelbergling.corntime.core.data.local.createPreferencesDataStore
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