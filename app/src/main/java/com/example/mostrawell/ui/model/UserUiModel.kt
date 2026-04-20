package com.example.mostrawell.ui.model

import com.example.mostrawell.domain.entity.tag.Tag

class UserUiModel (
    val id: Long,
    val name: String,
    val age: String,
    val avatarUrl: String?,
    val tags: List<Tag>
)