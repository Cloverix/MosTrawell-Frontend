package com.example.mostrawell.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: Long,
    val login: String,
    val name: String,
    val age: Int,
    val avatarUrl: String?,
    val tags: Set<String>
)