package com.example.mobiledevandroide.utils

import android.app.Application
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobiledevandroide.data.dao.ReceiptDao
import com.example.mobiledevandroide.data.entity.ReceiptEntity
import dagger.hilt.android.HiltAndroidApp

@Database(entities = [ReceiptEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun receiptDao(): ReceiptDao
}

@HiltAndroidApp
class MyApp : Application()