package com.example.mobiledevandroide.data.entity

import android.graphics.BitmapFactory
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mobiledevandroide.data.model.ReceiptModel

@Entity(tableName = "receipts")
data class ReceiptEntity(
    @PrimaryKey val id: Int,
    val totalAmount: String,
    val currency: String,
    val date: String,
    val paymentType: String,
    val receiptImageId: Int,
    val receiptImage: ByteArray
)
fun ReceiptEntity.toReceiptModel(): ReceiptModel {
    return ReceiptModel(
        id = id,
        totalAmount = totalAmount,
        currency = currency,
        date = date,
        paymentType = paymentType,
        receiptImageId = receiptImageId,
        receiptImage = BitmapFactory.decodeByteArray(receiptImage, 0, receiptImage.size)
    )
}