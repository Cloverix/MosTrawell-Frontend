package com.example.mostrawell.ui.model

import com.example.mostrawell.domain.entity.tag.Tag

class UserUiModel (
    val name: String,
    val age: Int,
    val avatarUrl: String?,
    val tags: List<Tag>
)