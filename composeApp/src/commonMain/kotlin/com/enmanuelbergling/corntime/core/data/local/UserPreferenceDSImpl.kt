package com.enmanuelbergling.corntime.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.enmanuelbergling.core.domain.datasource.preferences.UserPreferenceDS
import com.enmanuelbergling.core.model.user.UserDetails
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private object Keys {
    val USER_ID = intPreferencesKey("user_id")
    val USERNAME = stringPreferencesKey("username")
    val USER_AVATAR_PATH = stringPreferencesKey("use_avatar_path")
    val USER_NAME = stringPreferencesKey("user_name")
}

class UserPreferenceDSImpl(private val dataStore: DataStore<Preferences>) : UserPreferenceDS {
    override fun getCurrentUser(): Flow<UserDetails?> = dataStore.data.map {
        val username = it[Keys.USERNAME] ?: return@map null
        if (username.isBlank()) null
        else it.toModel()
    }

    override suspend fun updateUser(userDetails: UserDetails) {
        dataStore.edit {
            it[Keys.USER_ID] = userDetails.id
            it[Keys.USERNAME] = userDetails.username
            it[Keys.USER_AVATAR_PATH] = userDetails.avatarPath
            it[Keys.USER_NAME] = userDetails.name
        }
    }

    override suspend fun clear() {
        dataStore.edit { it.clear() }
    }
}

internal fun Preferences.toModel() = UserDetails(
    id = this[Keys.USER_ID] ?: 0,
    username = this[Keys.USERNAME] ?: "",
    avatarPath = this[Keys.USER_AVATAR_PATH] ?: "",
    name = this[Keys.USER_NAME] ?: "",
)