package com.example.mostrawell.data.remote.dto

data class LandmarkDto(
    val name: String,
    val address: String,
    val desc: String,
    val tags: List<String>
)