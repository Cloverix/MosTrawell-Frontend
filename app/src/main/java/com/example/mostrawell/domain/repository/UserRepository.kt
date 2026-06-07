package com.example.mostrawell.domain.repository

import com.example.mostrawell.data.remote.dto.UserRegisterDto
import com.example.mostrawell.domain.util.Resource
import com.example.mostrawell.ui.model.LandmarkUiModel
import com.example.mostrawell.ui.model.UserUiModel

interface UserRepository {
    //GET
    suspend fun getById(id: Long): Resource<UserUiModel>
    suspend fun getByLogin(login: String): Resource<UserUiModel>
    suspend fun login(login: String, password: String): Resource<UserUiModel>
    suspend fun getFavourites(id: Long): Resource<List<LandmarkUiModel>>
    //POST
    suspend fun register(dto: UserRegisterDto): Resource<UserUiModel>
    //PATCH
    suspend fun changeName(id: Long, name: String): Resource<UserUiModel>
    suspend fun changeTags(id: Long, tags: Set<String>): Resource<UserUiModel>
    suspend fun addFavouriteLandmark(id: Long, landmarkId: Long): Resource<UserUiModel>
    suspend fun removeFavouriteLandmark(id: Long, landmarkId: Long): Resource<UserUiModel>
    //DELETE
    suspend fun deleteUser(): Resource<Unit>
}