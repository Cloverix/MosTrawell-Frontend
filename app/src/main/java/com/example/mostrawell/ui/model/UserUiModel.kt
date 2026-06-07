package com.example.mostrawell.ui.model

import com.example.mostrawell.domain.entity.tag.Tag

class UserUiModel (
    val id: Long,
    val login: String,
    val name: String,
    val age: String,
    val avatarUrl: String?,
    val tags: Set<Tag>,
    val favouriteLandmarksId: List<Long>
) {
    override fun equals(other: Any?): Boolean {
        if (other !is UserUiModel) return false
        return id == other.id
    }
}