package com.example.mobiledevandroide.data.model

import android.graphics.Bitmap
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable

@Serializable
data class ReceiptModel(
    val id: Int,
    val totalAmount: String,
    val currency: String,
    val date: String,
    val paymentType: String,
    val receiptImageId: Int,
    @Contextual var receiptImage: Bitmap
)
