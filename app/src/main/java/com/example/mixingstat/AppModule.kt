package com.example.mixingstat

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.example.mixingstat.data.database.CocktailDatabase
import com.example.mixingstat.data.network.CocktailApiService
import com.example.mixingstat.data.network.RetrofitClient
import com.example.mixingstat.data.repository.CocktailRepository
import com.example.mixingstat.data.repository.CocktailRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Hilt Module for providing application-wide dependencies.
 *
 * This module provides the following dependencies:
 * - CocktailDatabase: The Room database for storing cocktail data.
 * - CocktailApiService: The Retrofit service for making network requests to the cocktail API.
 * - CocktailRepository: The repository for accessing cocktail data.
 * - ConnectivityManager: The system service for checking network connectivity.
 */
@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    /**
     * Provides the Room database for storing cocktail data.
     *
     * @param app The application context.
     * @return The Room database.
     */
    @Provides
    @Singleton
    fun provideMyDataBase(app: Application): CocktailDatabase {
        return Room.databaseBuilder(
            app,
            CocktailDatabase::class.java,
            "CocktailDatabase"
        ).build()
    }

    /**
     * Provides the Retrofit service for making network requests to the cocktail API.
     *
     * @return The Retrofit service.
     */
    @Provides
    @Singleton
    fun provideCocktailApiService(): CocktailApiService {
        return RetrofitClient.instance
    }

    /**
     * Provides the repository for accessing cocktail data.
     *
     * @param mydb The Room database.
     * @param apiService The Retrofit service.
     * @return The cocktail repository.
     */
    @Provides
    @Singleton
    fun provideMyRepository(mydb: CocktailDatabase, apiService: CocktailApiService): CocktailRepository {
        return CocktailRepositoryImpl(mydb.dao, apiService)
    }

    /**
     * Provides the system service for checking network connectivity.
     *
     * @param context The application context.
     * @return The connectivity manager.
     */
    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}