package com.example.mobiledevandroide.data.repository

import com.example.mobiledevandroide.data.dao.ReceiptDao
import com.example.mobiledevandroide.data.entity.ReceiptEntity
import javax.inject.Inject

class LocalReceiptsRepo @Inject constructor(
    private val receiptDao: ReceiptDao
) {


    fun saveLocalReceipt(localReceipt: ReceiptEntity) {
        receiptDao.insertReceipt(localReceipt)
    }

    suspend fun getLocalReceipt(receiptId: String): ReceiptEntity? {
        return receiptDao.getReceiptById(receiptId)
    }

}