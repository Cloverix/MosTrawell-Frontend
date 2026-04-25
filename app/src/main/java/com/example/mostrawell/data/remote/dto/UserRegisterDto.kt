package com.example.mostrawell.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserRegisterDto(
    val name: String,
    val age: Int,
    val login: String,
    val password: String
)