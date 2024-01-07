package com.example.mobiledevandroide.data.model

import android.graphics.Bitmap
import com.example.mobiledevandroide.data.entity.ReceiptEntity
import kotlinx.serialization.Contextual
import kotlinx.serialization.Serializable
import java.io.ByteArrayOutputStream

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
fun ReceiptModel.toReceiptEntity(): ReceiptEntity {
    val stream = ByteArrayOutputStream()
    receiptImage.compress(Bitmap.CompressFormat.PNG, 100, stream)
    val byteArray = stream.toByteArray()

    return ReceiptEntity(
        id = id,
        totalAmount = totalAmount,
        currency = currency,
        date = date,
        paymentType = paymentType,
        receiptImageId = receiptImageId,
        receiptImage = byteArray
    )
}