package com.enmanuelbergling.core.local.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.enmanuelbergling.core.local.PREFERENCES_FILENAME
import com.enmanuelbergling.core.local.createPreferencesDataStore
import org.koin.core.module.Module
import org.koin.dsl.module
import java.io.File

actual val platformPreferencesModule: Module
    get() = module {
        single{ createDataStore() }
    }

private fun createDataStore(): DataStore<Preferences> = createPreferencesDataStore(
    producePath = {
        val file = File(System.getProperty("java.io.tmpdir"), PREFERENCES_FILENAME)
        file.absolutePath
    }
)