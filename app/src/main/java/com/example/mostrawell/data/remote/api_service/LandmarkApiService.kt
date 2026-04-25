package com.example.mostrawell.data.remote.api_service

import com.example.mostrawell.data.remote.dto.LandmarkDto
import com.example.mostrawell.domain.entity.tag.Tag
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class LandmarkApiService(private val client: HttpClient) {
    suspend fun getById(id: Long): LandmarkDto {
        return client.get("/$id").body()
    }

    suspend fun getByName(name: String): List<LandmarkDto> {
        return client.get("/search/byName") {
            parameter("name", name)
        }.body()
    }

    suspend fun getByAddress(address: String): List<LandmarkDto> {
        return client.get("/search/byAddress") {
            parameter("address", address)
        }.body()
    }

    suspend fun getByTags(tagNames: Set<String>): List<LandmarkDto> {
        return client.get("/search/byTags") {
            contentType(ContentType.Application.Json)
            setBody(tagNames)
        }.body()
    }
}