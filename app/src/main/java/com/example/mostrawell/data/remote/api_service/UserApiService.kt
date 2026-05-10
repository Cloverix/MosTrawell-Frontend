package com.example.mostrawell.data.remote.api_service

import com.example.mostrawell.data.remote.AuthManager
import com.example.mostrawell.data.remote.dto.UserDto
import com.example.mostrawell.data.remote.dto.UserRegisterDto
import com.example.mostrawell.domain.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.basicAuth
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.util.encodeBase64
import io.ktor.utils.io.CancellationException
import okhttp3.Response

class UserApiService(
    private val client: HttpClient,
    private val authManager: AuthManager) {
    suspend fun getById(id: Long): Resource<UserDto> {
        return try {
            val credentials = authManager.getCredentials() ?: return Resource.Failure("User not authenticated")
            val response = client.get("api/users/$id") {
                basicAuth(credentials.first, credentials.second)
            }
            if (response.status.isSuccess()) {
                Resource.Success(response.body())
            }
            else {
                Resource.Failure("Error ${response.status.value}: ${response.body<String>()}")
            }
        }
        catch (e: CancellationException) {
            throw e
        }
        catch (e: Exception) {
            Resource.Failure("Unexpected error: ${e.message}")
        }
    }

    suspend fun getByLogin(login: String): Resource<UserDto> {
        return try {
            val credentials = authManager.getCredentials() ?: return Resource.Failure("User not authenticated")
            val response = client.get("api/users/searchByLogin/$login") {
                basicAuth(credentials.first, credentials.second)
            }
            if (response.status.isSuccess()) {
                Resource.Success(response.body())
            }
            else {
                Resource.Failure("Error ${response.status.value}: ${response.body<String>()}")
            }
        }
        catch (e: CancellationException) {
            throw e
        }
        catch (e: Exception) {
            Resource.Failure("Unexpected error: ${e.message}")
        }
    }

    suspend fun login(
        login: String,
        password: String
    ): Resource<UserDto> {
        val credentials = "$login:$password"
        val encodedCredentials = credentials.encodeBase64()
        return try {
            val response = client.get("api/users/login") {
                header(HttpHeaders.Authorization, "Basic $encodedCredentials")
            }
            if (response.status.isSuccess()) {
                Resource.Success(response.body())
            }
            else {
                Resource.Failure("Error ${response.status.value}: ${response.body<String>()}")
            }
        }
        catch (e: CancellationException) {
            throw e
        }
        catch (e: Exception) {
            Resource.Failure("Unexpected error: ${e.message}")
        }
    }

    suspend fun register(dto: UserRegisterDto): Resource<UserDto> {
        return try {
            val response = client.post("api/users/register") {
                contentType(ContentType.Application.Json)
                setBody(dto)
            }
            if (response.status.isSuccess()) {
                Resource.Success(response.body())
            }
            else {
                Resource.Failure("Error ${response.status.value}: ${response.body<String>()}")
            }
        }
        catch (e: CancellationException) {
            throw e
        }
        catch (e: Exception) {
            Resource.Failure("Unexpected error: ${e.message}")
        }
    }

    suspend fun changeName(
        id: Long,
        name: String
    ): Resource<UserDto> {
        return try{
            val credentials = authManager.getCredentials() ?: return Resource.Failure("User not authenticated")
            val response = client.patch("api/users/editName") {
                parameter("id", id)
                parameter("name", name)
                basicAuth(credentials.first, credentials.second)
            }
            if (response.status.isSuccess()) {
                Resource.Success(response.body())
            }
            else {
                Resource.Failure("Error ${response.status.value}: ${response.body<String>()}")
            }
        }
        catch (e: CancellationException) {
            throw e
        }
        catch (e: Exception) {
            Resource.Failure("Unexpected error: ${e.message}")
        }
    }

    suspend fun changeTags(
        id: Long,
        tags: Set<String>
    ): Resource<UserDto> {
        return try {
            val credentials = authManager.getCredentials() ?: return Resource.Failure("User not authenticated")
            val response = client.patch("api/users/editTags") {
                parameter("id", id)
                contentType(ContentType.Application.Json)
                setBody(tags)
                basicAuth(credentials.first, credentials.second)
            }
            if (response.status.isSuccess()) {
                Resource.Success(response.body())
            }
            else {
                Resource.Failure("Error ${response.status.value}: ${response.body<String>()}")
            }
        }
        catch (e: CancellationException) {
            throw e
        }
        catch (e: Exception) {
            Resource.Failure("Unexpected error: ${e.message}")
        }
    }

    suspend fun deleteById(id: Long): Resource<Unit> {
        return try {
            val credentials = authManager.getCredentials() ?: return Resource.Failure("User not authenticated")
            val response = client.delete("api/users/delete") {
                parameter("id", id)
                basicAuth(credentials.first, credentials.second)
            }
            if (response.status.isSuccess()) {
                Resource.Success(Unit)
            }
            else {
                Resource.Failure("Error ${response.status.value}: ${response.body<String>()}")
            }
        }
        catch (e: CancellationException) {
            throw e
        }
        catch (e: Exception) {
            Resource.Failure("Unexpected error: ${e.message}")
        }
    }
}