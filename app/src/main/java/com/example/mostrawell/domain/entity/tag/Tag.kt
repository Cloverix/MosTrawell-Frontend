package com.example.mostrawell.domain.entity.tag

sealed interface Tag {
    val originalName: String
    fun getFormattedName(): String
}