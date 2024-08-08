package com.example.mixingstat.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.mixingstat.data.models.Cocktail
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the Cocktail table.
 * This is an interface that provides methods to interact with the database.
 */
@Dao
interface CocktailDao {

    /**
     * Insert a cocktail into the database. If the cocktail already exists, replace it.
     * @param cocktail The cocktail to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cocktail: Cocktail)

    /**
     * Update a cocktail in the database.
     * @param cocktail The cocktail to update.
     */
    @Update
    suspend fun update(cocktail: Cocktail)

    /**
     * Delete a cocktail from the database.
     * @param cocktail The cocktail to delete.
     */
    @Delete
    suspend fun delete(cocktail: Cocktail)

    /**
     * Get all cocktails from the database.
     * @return A Flow of List of cocktails.
     */
    @Query("SELECT * FROM cocktail")
    fun getAllCocktails(): Flow<List<Cocktail>>

    /**
     * Get a cocktail by its id.
     * @param id The id of the cocktail to retrieve.
     * @return The cocktail with the given id.
     */
    @Query("SELECT * FROM cocktail WHERE idDrink = :id")
    suspend fun getCocktailById(id: String): Cocktail

    /**
     * Insert a list of cocktails into the database. If a cocktail already exists, replace it.
     * @param drinks The list of cocktails to insert.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(drinks: List<Cocktail>)

    /**
     * Search for cocktails by the first letter of their name.
     * @param value The first letter of the cocktail name.
     * @return A list of cocktails that match the search criteria.
     */
    @Query("SELECT * FROM cocktail WHERE strDrink LIKE :value || '%'")
    suspend fun searchCocktailsByFirstLetter(value: Char): List<Cocktail>

    /**
     * Search for cocktails by their name.
     * @param value The name of the cocktail.
     * @return A list of cocktails that match the search criteria.
     */
    @Query("SELECT * FROM cocktail WHERE strDrink LIKE '%' || :value || '%'")
    suspend fun searchCocktailByName(value: String): List<Cocktail>

    /**
     * Search for cocktails by ingredient name.
     * @param value The name of the ingredient.
     * @return A list of cocktails that contain the given ingredient.
     */
    @Query("SELECT * FROM cocktail WHERE strIngredient1 LIKE '%' || :value || '%' OR strIngredient2 LIKE '%' || :value || '%' OR strIngredient3 LIKE '%' || :value || '%'")
    suspend fun searchByIngredientName(value: String): List<Cocktail>

    /**
     * Get a random cocktail from the database.
     * @return A random cocktail.
     */
    @Query("SELECT * FROM cocktail ORDER BY RANDOM() LIMIT 1")
    suspend fun getRandomCocktail(): Cocktail

    /**
     * Get all popular cocktails from the database.
     * @return A list of popular cocktails.
     */
    @Query("SELECT * FROM cocktail WHERE isPopular = 1")
    suspend fun getAllPopularCocktails(): List<Cocktail>

    /**
     * Get all the latest cocktails from the database.
     * @return A list of the latest cocktails.
     */
    @Query("SELECT * FROM cocktail WHERE isLatest = 1")
    suspend fun getAllLatestCocktails(): List<Cocktail>
}