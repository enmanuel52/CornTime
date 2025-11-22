package com.enmanuelbergling.core.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import com.enmanuelbergling.core.domain.datasource.preferences.SearchSuggestionDS
import com.enmanuelbergling.core.domain.datasource.preferences.StringQuery
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchSuggestionDSImpl(private val dataStore: DataStore<Preferences>): SearchSuggestionDS {

    private object Keys {
        val SUGGESTIONS = stringSetPreferencesKey("suggestions")
    }

    override suspend fun addSuggestion(query: StringQuery) {
        dataStore.edit {
            it[Keys.SUGGESTIONS] = it[Keys.SUGGESTIONS]?.plus(query) ?: setOf(query)
        }
    }

    override suspend fun deleteSuggestion(query: StringQuery) {
        dataStore.edit {
            it[Keys.SUGGESTIONS] = it[Keys.SUGGESTIONS]?.minus(query).orEmpty()
        }
    }

    override suspend fun clearSuggestions() {
        dataStore.edit {
            it.remove(Keys.SUGGESTIONS)
        }
    }

    override fun getSuggestions(): Flow<List<StringQuery>> =
        dataStore.data.map {
            it[Keys.SUGGESTIONS]?.toList().orEmpty()
        }

}