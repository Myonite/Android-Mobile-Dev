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

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideMyDataBase(app: Application): CocktailDatabase {
        return Room.databaseBuilder(
            app,
            CocktailDatabase::class.java,
            "CocktailDatabase"
        ).build()

    }

    @Provides
    @Singleton
    fun provideCocktailApiService(): CocktailApiService {
        return RetrofitClient.instance
    }

    @Provides
    @Singleton
    fun provideMyRepository(mydb: CocktailDatabase, apiService: CocktailApiService): CocktailRepository {
        return CocktailRepositoryImpl(mydb.dao, apiService)
    }


    @Provides
    @Singleton
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}