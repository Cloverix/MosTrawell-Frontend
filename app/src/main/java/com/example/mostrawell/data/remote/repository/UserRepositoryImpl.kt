package com.example.mostrawell.data.remote.repository

import com.example.mostrawell.data.remote.api_service.UserApiService
import com.example.mostrawell.data.remote.dto.UserRegisterDto
import com.example.mostrawell.domain.repository.UserRepository
import com.example.mostrawell.domain.util.Resource
import com.example.mostrawell.ui.mapper.UserMapper
import com.example.mostrawell.ui.model.UserUiModel
import io.ktor.client.plugins.ClientRequestException
import kotlinx.coroutines.CancellationException

class UserRepositoryImpl(private val userService: UserApiService): UserRepository {
    override suspend fun login(
        login: String,
        password: String
    ): Resource<UserUiModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getById(id: Long): Resource<UserUiModel> {
        return try {
            val responseDto = userService.getById(id)
            Resource.Success(UserMapper.mapDto(responseDto))
        } catch (e: ClientRequestException) {
            Resource.Failure("Error ${e.response.status.value}: ${e.response.status.description}")
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Resource.Failure("Unexpected error: ${e.message}")
        }
    }

    override suspend fun register(dto: UserRegisterDto): Resource<UserUiModel> {
        return try {
            val responseDto = userService.register(dto)
            Resource.Success(UserMapper.mapDto(responseDto))
        } catch (e: ClientRequestException) {
            Resource.Failure("Error ${e.response.status.value}: ${e.response.status.description}")
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Resource.Failure("Unexpected error: ${e.message}")
        }
    }

    override suspend fun changeName(
        id: Long,
        name: String
    ): Resource<UserUiModel> {
        return try {
            val responseDto = userService.changeName(id, name)
            Resource.Success(UserMapper.mapDto(responseDto))
        } catch (e: ClientRequestException) {
            Resource.Failure("Error ${e.response.status.value}: ${e.response.status.description}")
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Resource.Failure("Unexpected error: ${e.message}")
        }
    }

    override suspend fun changeTags(
        id: Long,
        tags: List<String>
    ): Resource<UserUiModel> {
        return try {
            val responseDto = userService.changeTags(id, tags)
            Resource.Success(UserMapper.mapDto(responseDto))
        } catch (e: ClientRequestException) {
            Resource.Failure("Error ${e.response.status.value}: ${e.response.status.description}")
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            Resource.Failure("Unexpected error: ${e.message}")
        }
    }

    override suspend fun deleteById(id: Long): Resource<Unit> {
        return try {
            Resource.Success(userService.deleteById(id))
        } catch (e: ClientRequestException) {
            Resource.Failure("Error ${e.response.status.value}: ${e.response.status.description}")
        } catch (e : CancellationException) {
            throw e
        } catch (e: Exception) {
            Resource.Failure("Unexpected error: ${e.message}")
        }
    }
}