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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(drinks: List<Cocktail>)

    @Query("SELECT * FROM cocktail WHERE strDrink LIKE :value || '%'")
    suspend fun searchCocktailsByFirstLetter(value: Char): List<Cocktail>

    @Query("SELECT * FROM cocktail WHERE strDrink LIKE '%' || :value || '%'")
    suspend fun searchCocktailByName(value: String): List<Cocktail>

    @Query("SELECT * FROM cocktail WHERE strIngredient1 LIKE '%' || :value || '%' OR strIngredient2 LIKE '%' || :value || '%' OR strIngredient3 LIKE '%' || :value || '%'")
    suspend fun searchByIngredientName(value: String): List<Cocktail>

    @Query("SELECT * FROM cocktail ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomCocktail(): Cocktail

    @Query("SELECT * FROM cocktail WHERE isPopular = 1")
    suspend fun getAllPopularCocktails(): List<Cocktail>

    @Query("SELECT * FROM cocktail WHERE isLatest = 1")
    suspend fun getAllLatestCocktails(): List<Cocktail>
}