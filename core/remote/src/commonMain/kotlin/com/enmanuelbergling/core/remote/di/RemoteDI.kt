package com.enmanuelbergling.core.remote.di

import com.enmanuelbergling.core.domain.datasource.remote.ActorRemoteDS
import com.enmanuelbergling.core.domain.datasource.remote.AuthRemoteDS
import com.enmanuelbergling.core.domain.datasource.remote.MovieRemoteDS
import com.enmanuelbergling.core.domain.datasource.remote.UserRemoteDS
import com.enmanuelbergling.core.remote.ktor.datasource.ActorRemoteDSImpl
import com.enmanuelbergling.core.remote.ktor.datasource.AuthRemoteDSImpl
import com.enmanuelbergling.core.remote.ktor.datasource.MovieRemoteDSImpl
import com.enmanuelbergling.core.remote.ktor.datasource.UserRemoteDSImpl
import com.enmanuelbergling.core.remote.ktor.getKtorClient
import com.enmanuelbergling.core.remote.ktor.service.ActorService
import com.enmanuelbergling.core.remote.ktor.service.AuthService
import com.enmanuelbergling.core.remote.ktor.service.MovieService
import com.enmanuelbergling.core.remote.ktor.service.UserService
import com.enmanuelbergling.core.remote.ktorfit.KtorfitClient
import com.enmanuelbergling.core.remote.ktorfit.service.MoviesFilterService
import com.enmanuelbergling.core.remote.ktorfit.service.MoviesSearchService
import com.enmanuelbergling.core.remote.ktorfit.service.createMoviesFilterService
import com.enmanuelbergling.core.remote.ktorfit.service.createMoviesSearchService
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

/**
 * Ktor engine
 * */
expect val platformRemoteModule: Module

val remoteModule = module {
    includes(platformRemoteModule)

    single { getKtorClient(get()) }

    single<MoviesFilterService> { KtorfitClient.createMoviesFilterService() }
    single<MoviesSearchService> { KtorfitClient.createMoviesSearchService() }

    singleOf(::MovieService)

    singleOf(::ActorService)

    singleOf(::AuthService)

    singleOf(::UserService)
}

val remoteDsModule = module {
    singleOf(::MovieRemoteDSImpl).bind(MovieRemoteDS::class)

    singleOf(::ActorRemoteDSImpl).bind(ActorRemoteDS::class)

    singleOf(::AuthRemoteDSImpl).bind(AuthRemoteDS::class)

    singleOf(::UserRemoteDSImpl).bind(UserRemoteDS::class)
}