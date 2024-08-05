package com.example.mixingstat.data.repository

import com.example.mixingstat.data.dao.CocktailDao
import com.example.mixingstat.data.models.Cocktail
import com.example.mixingstat.data.network.CocktailApiService
import com.example.mixingstat.data.response.CocktailsResponse
import com.example.mixingstat.utils.safeApiCall
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class CocktailRepositoryImpl @Inject constructor(
    private val dao: CocktailDao,
    private val apiService: CocktailApiService
) : CocktailRepository {


    private val dispatcher = Dispatchers.IO
    override suspend fun insert(cocktail: Cocktail) {
        withContext(dispatcher) {
            dao.insert(cocktail)
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
        return performRequest(
            apiCall = { apiService.getCocktail(id) },
            dbCall = { CocktailsResponse(listOf(dao.getCocktailById(id))) },
            processResponse = { response -> synchronizeDatabase(response.drinks) }
        ).drinks.firstOrNull()
    }

    override suspend fun getRandomCocktail(): Cocktail? {
        return withContext(dispatcher) {
            safeApiCall(
                apiCall = {
                    val response = apiService.getRandomCocktail()
                    val cocktailsResponse = response.body()
                    if (cocktailsResponse != null && cocktailsResponse.drinks.isNotEmpty()) {
                        cocktailsResponse.drinks[0]
                    } else {
                        null
                    }
                },
                dbCall = {      dao.getRandomCocktail()}
            )
        }
    }

    override suspend fun getAllPopularCocktails(): List<Cocktail> {
        return withContext(dispatcher) {
            safeApiCall(
                apiCall = {
                    val response = apiService.getAllPopularCocktails()
                    val cocktailsResponse = response.body()
                    if (cocktailsResponse != null && cocktailsResponse.drinks.isNotEmpty()) {
                        cocktailsResponse.drinks.forEach { it.isPopular = true }
                        dao.insertAll(cocktailsResponse.drinks)
                        cocktailsResponse.drinks
                    } else {
                        emptyList<Cocktail>()
                    }
                },
                dbCall = { dao.getAllPopularCocktails() }
            )
        }
    }

    override suspend fun getAllLatestCocktails(): List<Cocktail> {
        return withContext(dispatcher) {
            safeApiCall(
                apiCall = {
                    val response = apiService.getAllLatestCocktails()
                    val cocktailsResponse = response.body()
                    if (cocktailsResponse != null && cocktailsResponse.drinks.isNotEmpty()) {
                        cocktailsResponse.drinks.forEach { it.isLatest = true }
                        dao.insertAll(cocktailsResponse.drinks)
                        cocktailsResponse.drinks
                    } else {
                        emptyList<Cocktail>()
                    }
                },
                dbCall = { dao.getAllLatestCocktails() }
            )
        }
    }

    override suspend fun searchCocktailsByFirstLetter(value: Char): List<Cocktail>? {
        return withContext(dispatcher) {
            safeApiCall(
                apiCall = {
                    val response = apiService.searchCocktailsByFirstLetter(value)
                    val cocktailsResponse = response.body()
                    if (cocktailsResponse?.drinks?.isNotEmpty() == true) {
                        dao.insertAll(cocktailsResponse.drinks)
                        cocktailsResponse.drinks
                    } else {
                        emptyList<Cocktail>()
                    }
                },
                dbCall = { dao.searchCocktailsByFirstLetter(value) }
            )
        }
    }

    override suspend fun searchCocktailByName(value: String): List<Cocktail>? {
        return withContext(dispatcher) {
            safeApiCall(
                apiCall = {
                    val response = apiService.searchCocktailByName(value)
                    val cocktailsResponse = response.body()
                    if (cocktailsResponse?.drinks?.isNotEmpty() == true) {
                        dao.insertAll(cocktailsResponse.drinks)
                        cocktailsResponse.drinks
                    } else {
                        emptyList()
                    }
                },
                dbCall = { dao.searchCocktailByName(value) }
            )
        }
    }

    override suspend fun searchByIngredientName(value: String): List<Cocktail> {
        return withContext(dispatcher) {
            safeApiCall(
                apiCall = {
                    val response = apiService.searchByIngredientName(value)
                    val cocktailsResponse = response.body()
                    if (cocktailsResponse?.drinks?.isNotEmpty() == true) {
                        dao.insertAll(cocktailsResponse.drinks)
                        cocktailsResponse.drinks
                    } else {
                        emptyList()
                    }
                },
                dbCall = { dao.searchByIngredientName(value) }
            )
        }
    }

    private suspend fun synchronizeDatabase(cocktails: List<Cocktail>) {
        withContext(dispatcher) {
            dao.insertAll(cocktails)
        }
    }
    private suspend fun <T> performRequest(
        apiCall: suspend () -> Response<T>,
        dbCall: suspend () -> T?,
        processResponse: suspend (T) -> Unit
    ): T {
        return withContext(dispatcher) {
            safeApiCall(
                apiCall = {
                    val response = apiCall.invoke()
                    val responseBody = response.body()
                    if (responseBody != null) {
                        processResponse(responseBody)
                    }
                    responseBody ?: throw IOException("Response body is null")
                },
                dbCall = { dbCall.invoke() ?: throw IOException("Database call returned null") }
            )
        }
    }
}
