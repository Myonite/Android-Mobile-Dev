package com.example.mixingstat.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mixingstat.data.models.Cocktail
import kotlinx.coroutines.flow.Flow

@Dao
interface CocktailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cocktail: Cocktail)

    @Update
    suspend fun update(cocktail: Cocktail)

    @Delete
    suspend fun delete(cocktail: Cocktail)

    @Query("SELECT * FROM cocktail")
    fun getAllCocktails(): Flow<List<Cocktail>>

    @Query("SELECT * FROM cocktail WHERE idDrink = :id")
    suspend fun getCocktailById(id: String): Cocktail
}