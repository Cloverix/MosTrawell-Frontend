package com.example.mostrawell.ui.mapper

import com.example.mostrawell.data.remote.dto.UserDto
import com.example.mostrawell.domain.entity.tag.Tag
import com.example.mostrawell.domain.util.findTagByName
import com.example.mostrawell.ui.model.UserUiModel

object UserDtoMapper {
    fun mapDto(dto: UserDto): UserUiModel {
        return UserUiModel(
            name = dto.name,
            age = dto.age,
            avatarUrl = dto.avatarUrl,
            tags = dto.tags.map { tagName ->
                val tag: Tag? = findTagByName(tagName)
                tag?:throw IllegalArgumentException("Tag does not exist")
                tag
            }
        )
    }

    fun mapUiModel(uiModel: UserUiModel, userId: Long): UserDto {
        return UserDto(
            id = userId,
            name = uiModel.name,
            age = uiModel.age,
            avatarUrl = uiModel.avatarUrl,
            tags = uiModel.tags.map { it.toString() }
        )
    }
}