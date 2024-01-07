package com.example.mobiledevandroide.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobiledevandroide.data.entity.ReceiptEntity

@Dao
interface ReceiptDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertReceipt(localReceiptDetail: ReceiptEntity)

    @Query("SELECT * FROM local_receipt")
    fun getAllReceipts(): List<ReceiptEntity>

    @Query("SELECT * FROM local_receipt WHERE receiptId = :receiptId")
    suspend fun getReceiptById(receiptId: String): ReceiptEntity?
}
