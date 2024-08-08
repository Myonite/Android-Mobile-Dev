package com.example.mixingstat.data.repository

import com.example.mixingstat.data.models.Cocktail

/**
 * Interface for the CocktailRepository.
 * This interface provides methods to interact with the Cocktail data both locally and from the network.
 */
interface CocktailRepository {

    /**
     * Insert a cocktail into the database.
     * @param cocktail The cocktail to insert.
     */
    suspend fun insert(cocktail: Cocktail)

    /**
     * Delete a cocktail from the database.
     * @param cocktail The cocktail to delete.
     */
    suspend fun delete(cocktail: Cocktail)

    /**
     * Update a cocktail in the database.
     * @param cocktail The cocktail to update.
     */
    suspend fun update(cocktail: Cocktail)

    /**
     * Get a cocktail by its id.
     * @param id The id of the cocktail to retrieve.
     * @return The cocktail with the given id.
     */
    suspend fun getCocktail(id: String): Cocktail?

    /**
     * Get a random cocktail from the database.
     * @return A random cocktail.
     */
    suspend fun getRandomCocktail(): Cocktail?

    /**
     * Get all popular cocktails from the database.
     * @return A list of popular cocktails.
     */
    suspend fun getAllPopularCocktails(): List<Cocktail>

    /**
     * Get all the latest cocktails from the database.
     * @return A list of the latest cocktails.
     */
    suspend fun getAllLatestCocktails(): List<Cocktail>

    /**
     * Search for cocktails by the first letter of their name.
     * @param value The first letter of the cocktail name.
     * @return A list of cocktails that match the search criteria.
     */
    suspend fun searchCocktailsByFirstLetter(value: Char): List<Cocktail>?

    /**
     * Search for cocktails by their name.
     * @param value The name of the cocktail.
     * @return A list of cocktails that match the search criteria.
     */
    suspend fun searchCocktailByName(value: String): List<Cocktail>?

    /**
     * Search for cocktails by ingredient name.
     * @param value The name of the ingredient.
     * @return A list of cocktails that contain the given ingredient.
     */
    suspend fun searchByIngredientName(value: String): List<Cocktail>?
}