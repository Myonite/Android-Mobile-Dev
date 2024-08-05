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

class CocktailRepositoryImpl @Inject constructor(
    private val dao: CocktailDao,
    private val apiService: CocktailApiService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val apiUtils: ApiUtils = ApiUtils(dispatcher),
) : CocktailRepository {


    override suspend fun insert(cocktail: Cocktail) {
        withContext(dispatcher) {
            dao.insert(cocktail)
        }
    }

    private suspend fun synchronizeDatabase(cocktails: List<Cocktail>) {
        withContext(dispatcher) {
            dao.insertAll(cocktails)
        }
    }

    override suspend fun delete(cocktail: Cocktail) {
        withContext(dispatcher) {
            dao.delete(cocktail)
        }
    }

    override suspend fun update(cocktail: Cocktail) {
        withContext(dispatcher) {
            dao.update(cocktail)
        }
    }

    override suspend fun getCocktail(id: String): Cocktail? {
        return apiUtils.performRequest(
            apiCall = { apiService.getCocktail(id) },
            dbCall = { CocktailsResponse(listOf(dao.getCocktailById(id))) },
            processResponse = { response -> synchronizeDatabase(response.drinks) }
        ).drinks.firstOrNull()
    }

    override suspend fun getRandomCocktail(): Cocktail? {
        return apiUtils.performRequest(
            apiCall = { apiService.getRandomCocktail() },
            dbCall = { CocktailsResponse(listOf(dao.getRandomCocktail())) },
            processResponse = { response -> synchronizeDatabase(response.drinks) }
        ).drinks.firstOrNull()
    }

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

    override suspend fun searchCocktailsByFirstLetter(value: Char): List<Cocktail> {
        return apiUtils.performRequest(
            apiCall = { apiService.searchCocktailsByFirstLetter(value) },
            dbCall = { CocktailsResponse(dao.searchCocktailsByFirstLetter(value)) },
            processResponse = {
                synchronizeDatabase(it.drinks)
            }
        ).drinks
    }

    override suspend fun searchCocktailByName(value: String): List<Cocktail> {
        return apiUtils.performRequest(
            apiCall = { apiService.searchCocktailByName(value) },
            dbCall = { CocktailsResponse(dao.searchCocktailByName(value)) },
            processResponse = {
                synchronizeDatabase(it.drinks)
            }
        ).drinks
    }

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
