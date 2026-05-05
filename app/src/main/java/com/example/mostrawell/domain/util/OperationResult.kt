package com.example.mostrawell.domain.util

sealed class OperationResult {
    object Loading: OperationResult()
    object Success: OperationResult()
    data class Failure(val message: String): OperationResult()
}