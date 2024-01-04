package com.example.mobiledevandroide.data.model

import com.google.gson.annotations.SerializedName


data class ProcessRequestModel(
    @SerializedName("totalAmount") val totalAmount: Double,
    @SerializedName("currency") val currency: String,
    @SerializedName("paymentType") val paymentType: String,
    @SerializedName("date") val date: String,
    @SerializedName("receiptImageId") val receiptImageId: Int,
)

