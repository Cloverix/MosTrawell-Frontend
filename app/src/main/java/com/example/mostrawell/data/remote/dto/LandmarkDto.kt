package com.example.mostrawell.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class LandmarkDto(
    val id: Long,
    val name: String,
    val address: String,
    val desc: String,
    val tags: List<String>
)