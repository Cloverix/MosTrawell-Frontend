package com.example.mostrawell.data.remote.api_service

import com.example.mostrawell.data.remote.dto.UserDto
import com.example.mostrawell.data.remote.dto.UserRegisterDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import java.lang.Exception

class UserApiService(private val client: HttpClient) {
    suspend fun login(
        login: String,
        password: String
    ): UserDto? {
        TODO("Not yet implemented")
    }

    suspend fun getById(id: Long): UserDto {
        return client.get("/$id").body()
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
        tags: List<String>
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