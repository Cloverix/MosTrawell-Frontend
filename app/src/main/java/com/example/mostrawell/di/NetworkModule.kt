package com.example.mostrawell.di

import com.example.mostrawell.BuildConfig
import com.example.mostrawell.data.remote.AuthManager
import com.example.mostrawell.data.remote.api_service.LandmarkApiService
import com.example.mostrawell.data.remote.api_service.UserApiService
import com.example.mostrawell.data.remote.repository.LandmarkRepositoryImpl
import com.example.mostrawell.data.remote.repository.UserRepositoryImpl
import com.example.mostrawell.domain.repository.LandmarkRepository
import com.example.mostrawell.domain.repository.UserRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpSend
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BasicAuthCredentials
import io.ktor.client.plugins.auth.providers.basic
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.request
import io.ktor.http.isSuccess
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import okhttp3.Interceptor
import okhttp3.internal.connection.ConnectInterceptor.intercept
import org.koin.dsl.module

val networkModule = module {
    single { provideHttpClient(get()) }
    single { UserApiService(get(), get()) }
    single { LandmarkApiService(get(), get()) }
    single<UserRepository> { UserRepositoryImpl(get(), get()) }
    single<LandmarkRepository> { LandmarkRepositoryImpl(get()) }
}

private fun provideHttpClient(authManager: AuthManager): HttpClient {
    return HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            url.takeFrom(BuildConfig.BASE_URL)
        }
    }
}