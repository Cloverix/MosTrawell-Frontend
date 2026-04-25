package com.example.mostrawell.di

import com.example.mostrawell.BuildConfig
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
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.http.isSuccess
import io.ktor.http.takeFrom
import io.ktor.serialization.kotlinx.json.json
import org.koin.dsl.module

val networkModule = module {
    single { provideHttpClient() }
    single { UserApiService(get()) }
    single { LandmarkApiService(get()) }
    single<UserRepository> { UserRepositoryImpl(get()) }
    single<LandmarkRepository> { LandmarkRepositoryImpl(get()) }
}

private fun provideHttpClient(): HttpClient {
    return HttpClient(Android) {
        install(ContentNegotiation) {
            json()
        }
        defaultRequest {
            url.takeFrom(BuildConfig.BASE_URL)
        }
        HttpResponseValidator {
            validateResponse { response ->
                if (!response.status.isSuccess()) {
                    throw ClientRequestException(response, "${response.status.value}")
                }
            }
        }
    }
}