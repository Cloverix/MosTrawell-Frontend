package com.example.mostrawell.data.remote.api_service

import com.example.mostrawell.data.remote.dto.UserDto
import com.example.mostrawell.data.remote.dto.UserRegisterDto
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
import java.lang.Exception
import java.util.Base64
import kotlin.math.log

class UserApiService(private val client: HttpClient) {
    suspend fun getById(id: Long): UserDto {
        return client.get("/$id").body()
    }

    suspend fun getByLogin(login: String): UserDto {
        return client.get("/searchByLogin/$login").body()
    }

    suspend fun login(
        login: String,
        password: String
    ): UserDto {
        val credentials = "$login:$password"
        val encodedCredentials = credentials.encodeBase64()
        return client.get("/login") {
            header(HttpHeaders.Authorization, "Basic $encodedCredentials")
        }.body()
    }

    suspend fun register(dto: UserRegisterDto): UserDto {
        return client.post("/register") {
            contentType(ContentType.Application.Json)
            setBody(dto)
        }.body()
    }

    suspend fun changeName(
        id: Long,
        name: String
    ): UserDto {
        return client.patch("/editName") {
            parameter("id", id)
            parameter("name", name)
        }.body()
    }

    suspend fun changeTags(
        id: Long,
        tags: Set<String>
    ): UserDto {
        return client.patch("/editTags") {
            parameter("id", id)
            contentType(ContentType.Application.Json)
            setBody(tags)
        }.body()
    }

    suspend fun deleteById(id: Long) {
        client.delete("/delete") {
            parameter("id", id)
        }
    }
}