package com.enmanuelbergling.corntime.core.domain.usecase.movie.search

import com.enmanuelbergling.corntime.core.domain.datasource.preferences.SearchSuggestionDS

class ClearSearchSuggestionsUC(
    private val repo: SearchSuggestionDS
) {
    suspend operator fun invoke()=repo.clearSuggestions()
}