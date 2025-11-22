package com.enmanuelbergling.feature.watchlists.paging

import com.enmanuelbergling.core.domain.datasource.remote.UserRemoteDS
import com.enmanuelbergling.core.model.core.PageModel
import com.enmanuelbergling.core.model.core.ResultHandler
import com.enmanuelbergling.core.model.user.AccountListsFilter
import com.enmanuelbergling.core.model.user.WatchList
import com.enmanuelbergling.core.ui.core.GenericPagingSource

internal class UserWatchListsSource(service: UserRemoteDS, filter: AccountListsFilter) :
    GenericPagingSource<WatchList>(
        request = { page ->
            when (val result = service.getWatchLists(filter.sessionId, page)) {
                is ResultHandler.Error -> PageModel(emptyList(), 0)
                is ResultHandler.Success -> result.data ?: PageModel(emptyList(), 0)
            }
        }
    )