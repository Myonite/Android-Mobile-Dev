package com.example.mobiledevandroide.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UploadResponse(
    val data: DataModel,
)


@Serializable
data class DataModel(
    @SerialName("totalAmount") val totalAmount: Double,
    @SerialName("currency") val currency: String,
    @SerialName("paymentType") val paymentType: String,
    @SerialName("date") val date: String,
    @SerialName("receiptImageId") val receiptImageId: Int,
)
