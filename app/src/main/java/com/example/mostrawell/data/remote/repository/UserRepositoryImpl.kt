package com.example.mostrawell.data.remote.repository

import com.example.mostrawell.data.remote.api_service.UserApiService
import com.example.mostrawell.data.remote.dto.UserRegisterDto
import com.example.mostrawell.domain.repository.UserRepository
import com.example.mostrawell.domain.util.ProfileManager
import com.example.mostrawell.domain.util.Resource
import com.example.mostrawell.ui.mapper.LandmarkMapper
import com.example.mostrawell.ui.mapper.UserMapper
import com.example.mostrawell.ui.model.LandmarkUiModel
import com.example.mostrawell.ui.model.UserUiModel

class UserRepositoryImpl(
    private val userService: UserApiService,
    private val profileManager: ProfileManager
): UserRepository {
    override suspend fun getById(id: Long): Resource<UserUiModel> {
        return when (val response = userService.getById(id)) {
            is Resource.Success -> Resource.Success(UserMapper.mapDto(response.data))
            is Resource.Failure -> response
        }
    }

    override suspend fun getByLogin(login: String): Resource<UserUiModel> {
        return when (val response = userService.getByLogin(login)) {
            is Resource.Success -> Resource.Success(UserMapper.mapDto(response.data))
            is Resource.Failure -> response
        }
    }

    override suspend fun login(
        login: String,
        password: String
    ): Resource<UserUiModel> {
        return when (val response = userService.login(login, password)) {
            is Resource.Success -> Resource.Success(UserMapper.mapDto(response.data))
            is Resource.Failure -> response
        }
    }

    override suspend fun getFavourites(id: Long): Resource<List<LandmarkUiModel>> {
        return when (val response = userService.getFavourites(id)) {
            is Resource.Success -> Resource.Success(
                response.data.map { LandmarkMapper.mapDto(it) }.toList()
            )
            is Resource.Failure -> response
        }
    }

    override suspend fun register(dto: UserRegisterDto): Resource<UserUiModel> {
        return when (val response = userService.register(dto)) {
            is Resource.Success -> Resource.Success(UserMapper.mapDto(response.data))
            is Resource.Failure -> response
        }
    }

    override suspend fun changeName(
        id: Long,
        name: String
    ): Resource<UserUiModel> {
        return when (val response = userService.changeName(id, name)) {
            is Resource.Success -> Resource.Success(UserMapper.mapDto(response.data))
            is Resource.Failure -> response
        }
    }

    override suspend fun changeTags(
        id: Long,
        tags: Set<String>
    ): Resource<UserUiModel> {
        return when (val response = userService.changeTags(id, tags)) {
            is Resource.Success -> Resource.Success(UserMapper.mapDto(response.data))
            is Resource.Failure -> response
        }
    }

    override suspend fun addFavouriteLandmark(
        id: Long,
        landmarkId: Long
    ): Resource<UserUiModel> {
        return when(val responce = userService.addFavouriteLandmark(id, landmarkId)) {
            is Resource.Success -> Resource.Success(UserMapper.mapDto(responce.data))
            is Resource.Failure -> responce
        }
    }

    override suspend fun removeFavouriteLandmark(
        id: Long,
        landmarkId: Long
    ): Resource<UserUiModel> {
        return when (val responce = userService.removeFavouriteLandmark(id, landmarkId)) {
            is Resource.Success -> Resource.Success(UserMapper.mapDto(responce.data))
            is Resource.Failure -> responce
        }
    }

    override suspend fun deleteUser(): Resource<Unit> {
        val userId = profileManager.getId() ?: return Resource.Failure("User not logged in")
        return when (val response = userService.deleteById(userId)) {
            is Resource.Success -> {
                profileManager.clear()
                Resource.Success(response.data)
            }
            is Resource.Failure -> response
        }
    }
}