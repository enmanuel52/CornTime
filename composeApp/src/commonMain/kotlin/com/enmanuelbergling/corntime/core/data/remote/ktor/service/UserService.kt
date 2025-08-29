package com.enmanuelbergling.corntime.core.data.remote.ktor.service

import com.enmanuelbergling.corntime.core.data.remote.ktor.KtorClient
import com.enmanuelbergling.corntime.core.data.remote.dto.user.UserDetailsDTO
import com.enmanuelbergling.corntime.core.data.remote.dto.user.watch.AccountListsPageDTO
import com.enmanuelbergling.corntime.core.data.remote.dto.user.watch.CheckItemListDTO
import com.enmanuelbergling.corntime.core.data.remote.dto.user.watch.CreateListBody
import com.enmanuelbergling.corntime.core.data.remote.dto.user.watch.MediaOnListBody
import com.enmanuelbergling.corntime.core.data.remote.dto.user.watch.MovieListPageDTO
import com.enmanuelbergling.corntime.core.data.remote.dto.user.watch.WatchResponseDTO
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

const val ACCOUNT_ID = "account_id"
class UserService(private val httpClient: KtorClient) {

    internal suspend fun getAccount(sessionId: String): UserDetailsDTO = httpClient
        .get("account/$ACCOUNT_ID") {
            url {
                parameters.append("session_id", sessionId)
            }
        }
        .body()

    internal suspend fun createWatchList(
        listBody: CreateListBody,
        sessionId: String,
    ): WatchResponseDTO = httpClient
        .post("list") {
            url {
                parameters.append("session_id", sessionId)
            }
            contentType(ContentType.Application.Json)
            setBody(listBody)
        }
        .body()

    internal suspend fun deleteMovieFromList(
        mediaBody: MediaOnListBody,
        listId: Int,
        sessionId: String,
    ): WatchResponseDTO = httpClient
        .post("list/$listId/remove_item") {
            url {
                parameters.append("session_id", sessionId)
            }
            contentType(ContentType.Application.Json)
            setBody(mediaBody)
        }
        .body()

    internal suspend fun addMovieToList(
        mediaBody: MediaOnListBody,
        listId: Int,
        sessionId: String,
    ): WatchResponseDTO = httpClient
        .post("list/$listId/add_item") {
            url {
                parameters.append("session_id", sessionId)
            }
            contentType(ContentType.Application.Json)
            setBody(mediaBody)
        }
        .body()

    internal suspend fun deleteList(
        listId: Int,
        sessionId: String,
    ): WatchResponseDTO = httpClient
        .delete("list/$listId") {
            url {
                parameters.append("session_id", sessionId)
            }
        }
        .body()

    internal suspend fun getListDetails(
        listId: Int,
        page: Int,
    ): MovieListPageDTO = httpClient
        .get("list/$listId") {
            url {
                parameters.append(name = "page", value = "$page")
            }
        }
        .body()

    internal suspend fun getAccountLists(
        accountId: String,
        sessionId: String,
        page: Int,
    ): AccountListsPageDTO = httpClient
        .get("account/$accountId/lists") {
            url {
                parameters.append(name = "page", value = "$page")
                parameters.append("session_id", sessionId)
            }
        }
        .body()

    internal suspend fun checkItemStatus(
        listId: Int,
        movieId: Int,
    ): CheckItemListDTO = httpClient
        .get("list/$listId/item_status") {
            url {
                parameters.append(name = "movie_id", value = "$movieId")
            }
        }
        .body()
}