package com.example.mostrawell.ui.mapper

import com.example.mostrawell.data.remote.dto.UserDto
import com.example.mostrawell.domain.entity.tag.Tag
import com.example.mostrawell.domain.util.findTagByName
import com.example.mostrawell.ui.model.UserUiModel
import kotlin.math.log

object UserMapper {
    fun mapDto(dto: UserDto): UserUiModel {
        return UserUiModel(
            id = dto.id,
            login = dto.login,
            name = dto.name,
            age = dto.age.toString(),
            avatarUrl = dto.avatarUrl,
            tags = dto.tags.map { tagName ->
                val tag: Tag? = findTagByName(tagName)
                tag?:throw IllegalArgumentException("Tag does not exist")
                tag
            }.toSet(),
            favouriteLandmarksId = dto.favouriteLandmarksId
        )
    }

    fun mapUiModel(uiModel: UserUiModel): UserDto {
        return UserDto(
            id = uiModel.id,
            login = uiModel.login,
            name = uiModel.name,
            age = uiModel.age.toInt(),
            avatarUrl = uiModel.avatarUrl,
            tags = uiModel.tags.map { it.toString() }.toSet(),
            favouriteLandmarksId = uiModel.favouriteLandmarksId
        )
    }
}