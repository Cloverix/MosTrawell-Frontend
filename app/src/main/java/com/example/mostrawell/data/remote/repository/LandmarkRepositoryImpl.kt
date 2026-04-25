package com.example.mostrawell.data.remote.repository

import com.example.mostrawell.data.remote.api_service.LandmarkApiService
import com.example.mostrawell.data.remote.dto.LandmarkDto
import com.example.mostrawell.domain.entity.tag.Tag
import com.example.mostrawell.domain.repository.LandmarkRepository
import com.example.mostrawell.domain.util.Resource
import com.example.mostrawell.ui.mapper.LandmarkMapper
import com.example.mostrawell.ui.model.LandmarkUiModel
import io.ktor.client.plugins.ClientRequestException
import kotlinx.coroutines.CancellationException

class LandmarkRepositoryImpl(private val landmarkService: LandmarkApiService): LandmarkRepository {
    override suspend fun getById(id: Long): Resource<LandmarkUiModel> {
        return try {
            val responseDto = landmarkService.getById(id)
            Resource.Success(LandmarkMapper.mapDto(responseDto))
        } catch (e: ClientRequestException) {
            Resource.Failure("Error ${e.response.status.value}: ${e.response.status.description}")
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Resource.Failure("Unexpected error: ${e.message}")
        }
    }

    override suspend fun getByName(name: String): Resource<List<LandmarkUiModel>> {
        return try {
            val responseDtoList = landmarkService.getByName(name)
            Resource.Success(responseDtoList.map(LandmarkMapper::mapDto))
        } catch (e: ClientRequestException) {
            Resource.Failure("Error ${e.response.status.value}: ${e.response.status.description}")
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Resource.Failure("Unexpected error: ${e.message}")
        }
    }

    override suspend fun getByAddress(address: String): Resource<List<LandmarkUiModel>> {
        return try {
            val responseDtoList = landmarkService.getByAddress(address)
            Resource.Success(responseDtoList.map(LandmarkMapper::mapDto))
        } catch (e: ClientRequestException) {
            Resource.Failure("Error ${e.response.status.value}: ${e.response.status.description}")
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Resource.Failure("Unexpected error: ${e.message}")
        }
    }

    override suspend fun getByTags(tags: Set<Tag>): Resource<List<LandmarkUiModel>> {
        val tagNames = tags.map { tag -> tag.getName()}.toSet()
        return try {
            val responseDtoList = landmarkService.getByTags(tagNames)
            Resource.Success(responseDtoList.map(LandmarkMapper::mapDto))
        } catch (e: ClientRequestException) {
            Resource.Failure("Error ${e.response.status.value}: ${e.response.status.description}")
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Resource.Failure("Unexpected error: ${e.message}")
        }
    }
}