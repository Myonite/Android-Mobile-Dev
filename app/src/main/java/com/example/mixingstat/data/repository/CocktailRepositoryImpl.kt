package com.example.mixingstat.data.repository

import com.example.mixingstat.data.dao.CocktailDao
import com.example.mixingstat.data.models.Cocktail
import com.example.mixingstat.data.network.CocktailApiService
import com.example.mixingstat.data.response.CocktailsResponse
import com.example.mixingstat.utils.ApiUtils
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Implementation of the CocktailRepository interface.
 * This class provides methods to interact with the Cocktail data both locally and from the network.
 *
 * @property dao The data access object (DAO) to interact with the local database.
 * @property apiService The service to interact with the Cocktail API.
 * @property dispatcher The coroutine dispatcher to use for coroutine context.
 * @property apiUtils The utility class to perform API requests and process responses.
 */
class CocktailRepositoryImpl @Inject constructor(
    private val dao: CocktailDao,
    private val apiService: CocktailApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val apiUtils: ApiUtils = ApiUtils(dispatcher),
) : CocktailRepository {

    /**
     * Insert a cocktail into the database.
     * @param cocktail The cocktail to insert.
     */
    override suspend fun insert(cocktail: Cocktail) {
        withContext(dispatcher) {
            dao.insert(cocktail)
        }
    }

    /**
     * Synchronize the local database with the latest data from the API.
     * @param cocktails The list of cocktails to insert into the database.
     */
    private suspend fun synchronizeDatabase(cocktails: List<Cocktail>) {
        withContext(dispatcher) {
            dao.insertAll(cocktails)
        }
    }

    /**
     * Delete a cocktail from the database.
     * @param cocktail The cocktail to delete.
     */
    override suspend fun delete(cocktail: Cocktail) {
        withContext(dispatcher) {
            dao.delete(cocktail)
        }
    }

    /**
     * Update a cocktail in the database.
     * @param cocktail The cocktail to update.
     */
    override suspend fun update(cocktail: Cocktail) {
        withContext(dispatcher) {
            dao.update(cocktail)
        }
    }

    /**
     * Get a cocktail by its id.
     * @param id The id of the cocktail to retrieve.
     * @return The cocktail with the given id.
     */
    override suspend fun getCocktail(id: String): Cocktail? {
        return apiUtils.performRequest(
            apiCall = { apiService.getCocktail(id) },
            dbCall = { CocktailsResponse(listOf(dao.getCocktailById(id))) },
            processResponse = { response -> synchronizeDatabase(response.drinks) }
        ).drinks.firstOrNull()
    }

    /**
     * Get a random cocktail from the database.
     * @return A random cocktail.
     */
    override suspend fun getRandomCocktail(): Cocktail? {
        return apiUtils.performRequest(
            apiCall = { apiService.getRandomCocktail() },
            dbCall = { CocktailsResponse(listOf(dao.getRandomCocktail())) },
            processResponse = { response -> synchronizeDatabase(response.drinks) }
        ).drinks.firstOrNull()
    }

    /**
     * Get all popular cocktails from the database.
     * @return A list of popular cocktails.
     */
    override suspend fun getAllPopularCocktails(): List<Cocktail> {
        return apiUtils.performRequest(
            apiCall = { apiService.getAllPopularCocktails() },
            dbCall = { CocktailsResponse(dao.getAllPopularCocktails()) },
            processResponse = {
                it.drinks.forEach { it.isPopular = true }
                synchronizeDatabase(it.drinks)
            }
        ).drinks
    }

    /**
     * Get all the latest cocktails from the database.
     * @return A list of the latest cocktails.
     */
    override suspend fun getAllLatestCocktails(): List<Cocktail> {
        return apiUtils.performRequest(
            apiCall = { apiService.getAllLatestCocktails() },
            dbCall = { CocktailsResponse(dao.getAllLatestCocktails()) },
            processResponse = {
                it.drinks.forEach { it.isLatest = true }
                synchronizeDatabase(it.drinks)
            }
        ).drinks
    }

    /**
     * Search for cocktails by the first letter of their name.
     * @param value The first letter of the cocktail name.
     * @return A list of cocktails that match the search criteria.
     */
    override suspend fun searchCocktailsByFirstLetter(value: Char): List<Cocktail> {
        return apiUtils.performRequest(
            apiCall = { apiService.searchCocktailsByFirstLetter(value) },
            dbCall = { CocktailsResponse(dao.searchCocktailsByFirstLetter(value)) },
            processResponse = {
                synchronizeDatabase(it.drinks)
            }
        ).drinks
    }

    /**
     * Search for cocktails by their name.
     * @param value The name of the cocktail.
     * @return A list of cocktails that match the search criteria.
     */
    override suspend fun searchCocktailByName(value: String): List<Cocktail> {
        return apiUtils.performRequest(
            apiCall = { apiService.searchCocktailByName(value) },
            dbCall = { CocktailsResponse(dao.searchCocktailByName(value)) },
            processResponse = {
                synchronizeDatabase(it.drinks)
            }
        ).drinks
    }

    /**
     * Search for cocktails by ingredient name.
     * @param value The name of the ingredient.
     * @return A list of cocktails that contain the given ingredient.
     */
    override suspend fun searchByIngredientName(value: String): List<Cocktail> {
        return apiUtils.performRequest(
            apiCall = { apiService.searchByIngredientName(value) },
            dbCall = { CocktailsResponse(dao.searchByIngredientName(value)) },
            processResponse = {
                synchronizeDatabase(it.drinks)
            }
        ).drinks
    }
}