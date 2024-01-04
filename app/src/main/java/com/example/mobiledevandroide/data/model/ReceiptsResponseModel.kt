package com.example.mobiledevandroide.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReceiptsResponse(
    val data: DataWrapper,
)

@Serializable
data class DataWrapper(
    val data: List<ReceiptModel>,
    val pagination: Pagination,
)

@Serializable
data class Pagination(
    val page: Int,
    @SerialName("pageSize") val pageSize: Int,
    val totalPages: Int,
    val totalItems: Int,
)
