package com.example.mostrawell.ui.dto

import com.example.mostrawell.domain.entity.InterestTag

data class LandmarkDto(
    val name: String,
    val address: String,
    val desc: String,
    val labels: List<InterestTag>
)