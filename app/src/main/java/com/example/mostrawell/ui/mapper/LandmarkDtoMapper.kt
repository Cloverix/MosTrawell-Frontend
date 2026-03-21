package com.example.mostrawell.ui.mapper

import com.example.mostrawell.data.remote.dto.LandmarkDto
import com.example.mostrawell.ui.model.LandmarkUiModel

object LandmarkDtoMapper {
    fun mapDto(dto: LandmarkDto): LandmarkUiModel {
        return LandmarkUiModel(
            name = dto.name,
            address = dto.address,
            desc = dto.desc,
            labels = dto.labels
        )
    }

    fun mapUiModel(uiModel: LandmarkUiModel): LandmarkDto {
        return LandmarkDto(
            name = uiModel.name,
            address = uiModel.address,
            desc = uiModel.desc,
            labels = uiModel.labels
        )
    }
}