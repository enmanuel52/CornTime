package com.enmanuelbergling.corntime.core.domain.datasource.remote

import com.enmanuelbergling.corntime.core.model.core.NetworkException
import com.enmanuelbergling.corntime.core.model.core.ResultHandler

interface RemoteDataSource {

    suspend fun <T> safeKtorCall(request: suspend () -> T): ResultHandler<T> = try {
        val result = request()
        ResultHandler.Success(result)
    } catch (exception: NetworkException) {
        ResultHandler.Error(exception)
    } catch (exception: Exception) {
        ResultHandler.Error(NetworkException.DefaultException)
    }
}