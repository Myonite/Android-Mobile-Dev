package com.example.mobiledevandroide.module

import android.content.Context
import androidx.room.Room
import com.example.mobiledevandroide.utils.AppDatabase
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
class AppDatabaseProvider {

    fun provideRoomDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "your_database_name"
        ).build()
    }
}