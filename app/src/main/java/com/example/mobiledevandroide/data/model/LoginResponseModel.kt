package com.example.mobiledevandroide.data.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseModel(
    val data: String,
    val message: String,
)
