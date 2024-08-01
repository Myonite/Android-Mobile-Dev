package com.example.mixingstat.data.repository

import com.example.mixingstat.data.dao.CocktailDao
import com.example.mixingstat.data.models.Cocktail
import com.example.mixingstat.data.network.CocktailApiService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CocktailRepository {

    suspend fun insert(cocktail: Cocktail)
    suspend fun delete(cocktail: Cocktail)
    suspend fun update(cocktail: Cocktail)

    suspend fun getCocktailById(id: String): Cocktail
    suspend fun getAllCocktails(): Flow<List<Cocktail>>
    suspend fun getCocktailFromApi(id: String): Cocktail?
    suspend fun getRandomCocktailFromAPi(): Cocktail?
    suspend fun getAllPopularCocktails(): List<Cocktail>
    suspend fun getAllLatestCocktails(): List<Cocktail>
}

class CocktailRepositoryImpl @Inject constructor(
    private val dao: CocktailDao,
    private val apiService: CocktailApiService
) : CocktailRepository {
    override suspend fun insert(cocktail: Cocktail) {
        withContext(IO) {
            dao.insert(cocktail)
        }
    }

    override suspend fun delete(cocktail: Cocktail) {
        withContext(IO) {
            dao.delete(cocktail)
        }
    }

    override suspend fun update(cocktail: Cocktail) {
        withContext(IO) {
            dao.update(cocktail)
        }
    }

    override suspend fun getCocktailById(id: String): Cocktail {
        return withContext(IO) {
            dao.getCocktailById(id)
        }
    }

    override suspend fun getAllCocktails(): Flow<List<Cocktail>> {
        return withContext(IO) {
            dao.getAllCocktails()
        }
    }

    override suspend fun getCocktailFromApi(id: String): Cocktail? {
        return withContext(IO) {
            val response = apiService.getCocktail(id)
            val cocktailsResponse = response.body()
            if (cocktailsResponse != null && cocktailsResponse.drinks.isNotEmpty()) {
                cocktailsResponse.drinks[0]
            } else {
                null
            }
        }
    }

    override suspend fun getRandomCocktailFromAPi(): Cocktail? {
        return withContext(IO) {
            val response = apiService.getRandomCocktail()
            val cocktailsResponse = response.body()
            if (cocktailsResponse != null && cocktailsResponse.drinks.isNotEmpty()) {
                cocktailsResponse.drinks[0]
            } else {
                null
            }
        }
    }

    override suspend fun getAllPopularCocktails(): List<Cocktail> {
        return withContext(IO) {
            val response = apiService.getAllPopularCocktails()
            val cocktailsResponse = response.body()
            if (cocktailsResponse != null && cocktailsResponse.drinks.isNotEmpty()) {
                cocktailsResponse.drinks
            } else {
                emptyList()
            }
        }
    }

    override suspend fun getAllLatestCocktails(): List<Cocktail> {
        return withContext(IO) {
            val response = apiService.getAllLatestCocktails()
            val cocktailsResponse = response.body()
            if (cocktailsResponse != null && cocktailsResponse.drinks.isNotEmpty()) {
                cocktailsResponse.drinks
            } else {
                emptyList()
            }
        }
    }

}