package com.example.mostrawell.ui.model

import com.example.mostrawell.domain.entity.tag.Tag

class LandmarkUiModel (
    val name: String,
    val address: String,
    val desc: String,
    val labels: List<Tag>
)