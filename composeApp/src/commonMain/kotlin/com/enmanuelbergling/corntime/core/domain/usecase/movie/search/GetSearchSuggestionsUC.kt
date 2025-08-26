package com.enmanuelbergling.corntime.core.domain.usecase.movie.search

import com.enmanuelbergling.corntime.core.domain.datasource.preferences.SearchSuggestionDS

class GetSearchSuggestionsUC(
    private val repo: SearchSuggestionDS
) {
     operator fun invoke()=repo.getSuggestions()
}