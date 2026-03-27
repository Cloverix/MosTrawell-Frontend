package com.example.mostrawell.ui.mapper

import com.example.mostrawell.data.remote.dto.LandmarkDto
import com.example.mostrawell.domain.entity.tag.Tag
import com.example.mostrawell.domain.util.findTagByName
import com.example.mostrawell.ui.model.LandmarkUiModel

object LandmarkDtoMapper {
    fun mapDto(dto: LandmarkDto): LandmarkUiModel {
        return LandmarkUiModel(
            name = dto.name,
            address = dto.address,
            desc = dto.desc,
            tags = dto.tags.map { tagName ->
                val tag: Tag? = findTagByName(tagName)
                tag?:throw IllegalArgumentException("Tag does not exist")
                tag
            }
        )
    }

    fun mapUiModel(uiModel: LandmarkUiModel): LandmarkDto {
        return LandmarkDto(
            name = uiModel.name,
            address = uiModel.address,
            desc = uiModel.desc,
            tags = uiModel.tags.map { it.toString() }
        )
    }
}