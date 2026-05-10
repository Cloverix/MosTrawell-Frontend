package com.example.mostrawell.data.remote.api_service

import com.example.mostrawell.data.remote.AuthManager
import com.example.mostrawell.data.remote.dto.LandmarkDto
import com.example.mostrawell.domain.entity.tag.Tag
import com.example.mostrawell.domain.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.basicAuth
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.utils.io.CancellationException
import okhttp3.Response

class LandmarkApiService(
    private val client: HttpClient,
    private val authManager: AuthManager) {
    suspend fun getById(id: Long): Resource<LandmarkDto> {
        return try {
            val credentials = authManager.getCredentials() ?: return Resource.Failure("User not authorized")
            val response = client.get("api/landmark/$id") {
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

    suspend fun getByName(name: String): Resource<List<LandmarkDto>> {
        return try {
            val credentials = authManager.getCredentials() ?: return Resource.Failure("User not authorized")
            val response = client.get("api/landmark/search/byName") {
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

    suspend fun getByAddress(address: String): Resource<List<LandmarkDto>> {
        return try {
            val credentials = authManager.getCredentials() ?: return Resource.Failure("User not authorized")
            val response = client.get("api/landmark/search/byAddress") {
                parameter("address", address)
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

    suspend fun getByTags(tagNames: Set<String>): Resource<List<LandmarkDto>> {
        return try {
            val credentials = authManager.getCredentials() ?: return Resource.Failure("User not authorized")
            val response = client.get("api/landmark/search/byTags") {
                contentType(ContentType.Application.Json)
                tagNames.forEach { tag ->
                    parameter("tagNames", tag)
                }
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
}