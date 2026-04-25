package com.example.mostrawell.domain.repository

import com.example.mostrawell.data.remote.dto.LandmarkDto
import com.example.mostrawell.data.remote.dto.UserDto
import com.example.mostrawell.domain.entity.tag.Tag
import com.example.mostrawell.domain.util.Resource
import com.example.mostrawell.ui.model.LandmarkUiModel

interface LandmarkRepository {
    //GET
    suspend fun getById(id: Long): Resource<LandmarkUiModel>
    suspend fun getByName(name: String): Resource<List<LandmarkUiModel>>
    suspend fun getByAddress(address: String): Resource<List<LandmarkUiModel>>
    suspend fun getByTags(tags: Set<Tag>): Resource<List<LandmarkUiModel>>
    //POST
    //PUT
    //DELETE
}