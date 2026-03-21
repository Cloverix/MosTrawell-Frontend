package com.example.mostrawell.ui.mapper

import com.example.mostrawell.data.remote.dto.UserDto
import com.example.mostrawell.ui.model.UserUiModel

object UserDtoMapper {
    fun mapDto(dto: UserDto): UserUiModel {
        return UserUiModel(
            name = dto.name,
            age = dto.age,
            avatarUrl = dto.avatarUrl,
            tagList = dto.tagList
        )
    }

    fun mapUiModel(uiModel: UserUiModel): UserDto {
        return UserDto(
            name = uiModel.name,
            age = uiModel.age,
            avatarUrl = uiModel.avatarUrl,
            tagList = uiModel.tagList
        )
    }
}