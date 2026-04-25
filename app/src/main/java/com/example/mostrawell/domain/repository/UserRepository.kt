package com.example.mostrawell.domain.repository

import com.example.mostrawell.data.remote.dto.UserDto
import com.example.mostrawell.data.remote.dto.UserRegisterDto
import com.example.mostrawell.domain.util.Resource
import com.example.mostrawell.ui.model.UserUiModel

interface UserRepository {
    //GET
    suspend fun login(login: String, password: String): Resource<UserUiModel>
    suspend fun getById(id: Long): Resource<UserUiModel>
    //POST
    suspend fun register(dto: UserRegisterDto): Resource<UserUiModel>
    //PUT
    suspend fun changeName(id: Long, name: String): Resource<UserUiModel>
    suspend fun changeTags(id: Long, tags: List<String>): Resource<UserUiModel>
    //DELETE
    suspend fun deleteById(id: Long): Resource<Unit>
}