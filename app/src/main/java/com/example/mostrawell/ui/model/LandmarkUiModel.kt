package com.example.mostrawell.ui.model

import com.example.mostrawell.domain.entity.tag.Tag

class LandmarkUiModel (
    val id: Long,
    val name: String,
    val address: String,
    val desc: String,
    val tags: Set<Tag>
) {
    override fun equals(other: Any?): Boolean {
        if (other !is LandmarkUiModel) return false
        return id == other.id
    }
}