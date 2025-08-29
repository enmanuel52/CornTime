package com.enmanuelbergling.corntime.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.enmanuelbergling.corntime.core.domain.datasource.preferences.AuthPreferenceDS
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

class AuthPreferenceDSImpl(private val dataStore: DataStore<Preferences>) : AuthPreferenceDS {


    private object Keys {
        val SESSION_ID = stringPreferencesKey("session_id")
    }

    override fun saveSessionId(sessionId: String): Unit = runBlocking {
        dataStore.edit {
            it[Keys.SESSION_ID] = sessionId
        }
    }

    override fun getSessionId(): Flow<String> =
        dataStore.data.map { it[Keys.SESSION_ID].orEmpty() }

    override fun clear() {
        saveSessionId("")
    }
}