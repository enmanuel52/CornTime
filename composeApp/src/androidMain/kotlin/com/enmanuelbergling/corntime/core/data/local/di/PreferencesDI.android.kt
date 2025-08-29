package com.enmanuelbergling.corntime.core.data.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.enmanuelbergling.corntime.core.data.local.PREFERENCES_FILENAME
import com.enmanuelbergling.corntime.core.data.local.createPreferencesDataStore
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformPreferencesModule: Module
    get() = module { single { createDataStore(context = get()) } }

private fun createDataStore(context: Context): DataStore<Preferences> = createPreferencesDataStore(
    producePath = { context.filesDir.resolve(PREFERENCES_FILENAME).absolutePath }
)