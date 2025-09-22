package com.enmanuelbergling.corntime.core.data.remote.di

import com.enmanuelbergling.corntime.core.data.remote.ktor.datasource.ActorRemoteDSImpl
import com.enmanuelbergling.corntime.core.data.remote.ktor.datasource.AuthRemoteDSImpl
import com.enmanuelbergling.corntime.core.data.remote.ktor.datasource.MovieRemoteDSImpl
import com.enmanuelbergling.corntime.core.data.remote.ktor.datasource.UserRemoteDSImpl
import com.enmanuelbergling.corntime.core.data.remote.ktor.getKtorClient
import com.enmanuelbergling.corntime.core.data.remote.ktor.service.ActorService
import com.enmanuelbergling.corntime.core.data.remote.ktor.service.AuthService
import com.enmanuelbergling.corntime.core.data.remote.ktor.service.MovieService
import com.enmanuelbergling.corntime.core.data.remote.ktor.service.UserService
import com.enmanuelbergling.corntime.core.data.remote.ktorfit.KtorfitClient
import com.enmanuelbergling.corntime.core.data.remote.ktorfit.service.MoviesFilterService
import com.enmanuelbergling.corntime.core.data.remote.ktorfit.service.MoviesSearchService
import com.enmanuelbergling.corntime.core.data.remote.ktorfit.service.createMoviesFilterService
import com.enmanuelbergling.corntime.core.data.remote.ktorfit.service.createMoviesSearchService
import com.enmanuelbergling.corntime.core.domain.datasource.remote.ActorRemoteDS
import com.enmanuelbergling.corntime.core.domain.datasource.remote.AuthRemoteDS
import com.enmanuelbergling.corntime.core.domain.datasource.remote.MovieRemoteDS
import com.enmanuelbergling.corntime.core.domain.datasource.remote.UserRemoteDS
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
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
    single<MovieRemoteDS> { MovieRemoteDSImpl(get(), get(), get()) }

    single<ActorRemoteDS> { ActorRemoteDSImpl(get()) }

    single<AuthRemoteDS> { AuthRemoteDSImpl(get()) }

    single<UserRemoteDS> { UserRemoteDSImpl(get()) }
}