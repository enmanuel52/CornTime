package com.enmanuelbergling.corntime.core.domain.datasource.preferences

import com.enmanuelbergling.corntime.core.model.user.UserDetails
import kotlinx.coroutines.flow.Flow

interface UserPreferenceDS {

    fun getCurrentUser() : Flow<UserDetails?>

    suspend fun updateUser(userDetails: UserDetails)

    suspend fun clear()
}