package com.example.mostrawell.data.remote.repository

import com.example.mostrawell.data.remote.AuthManager
import com.example.mostrawell.data.remote.api_service.LandmarkApiService
import com.example.mostrawell.data.remote.dto.LandmarkDto
import com.example.mostrawell.domain.entity.tag.Tag
import com.example.mostrawell.domain.repository.LandmarkRepository
import com.example.mostrawell.domain.util.Resource
import com.example.mostrawell.ui.mapper.LandmarkMapper
import com.example.mostrawell.ui.model.LandmarkUiModel
import io.ktor.client.plugins.ClientRequestException
import kotlinx.coroutines.CancellationException

class LandmarkRepositoryImpl(
    private val landmarkService: LandmarkApiService): LandmarkRepository {
    override suspend fun getById(id: Long): Resource<LandmarkUiModel> {
        return when (val response = landmarkService.getById(id)) {
            is Resource.Success -> Resource.Success(LandmarkMapper.mapDto(response.data))
            is Resource.Failure -> response
        }
    }

    override suspend fun getByName(name: String): Resource<List<LandmarkUiModel>> {
        return when (val response = landmarkService.getByName(name)) {
            is Resource.Success -> Resource.Success(response.data.map(LandmarkMapper::mapDto))
            is Resource.Failure -> response
        }
    }

    override suspend fun getByAddress(address: String): Resource<List<LandmarkUiModel>> {
        return when (val response = landmarkService.getByAddress(address)) {
            is Resource.Success -> Resource.Success(response.data.map(LandmarkMapper::mapDto))
            is Resource.Failure -> response
        }
    }

    override suspend fun getByTags(tags: Set<Tag>): Resource<List<LandmarkUiModel>> {
        val tagNames = tags.map { tag -> tag.originalName}.toSet()
        return when (val response = landmarkService.getByTags(tagNames)) {
            is Resource.Success -> Resource.Success(response.data.map(LandmarkMapper::mapDto))
            is Resource.Failure -> response
        }
    }
}