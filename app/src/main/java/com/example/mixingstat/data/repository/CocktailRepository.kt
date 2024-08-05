package com.example.mixingstat.data.repository

import com.example.mixingstat.data.dao.CocktailDao
import com.example.mixingstat.data.models.Cocktail
import com.example.mixingstat.data.network.CocktailApiService
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

interface CocktailRepository {

    suspend fun insert(cocktail: Cocktail)
    suspend fun delete(cocktail: Cocktail)
    suspend fun update(cocktail: Cocktail)
    suspend fun getCocktail(id: String): Cocktail?
    suspend fun getRandomCocktail(): Cocktail?
    suspend fun getAllPopularCocktails(): List<Cocktail>
    suspend fun getAllLatestCocktails(): List<Cocktail>
    suspend fun searchCocktailsByFirstLetter(value: Char): List<Cocktail>?
    suspend fun searchCocktailByName(value: String): List<Cocktail>?
    suspend fun searchByIngredientName(value: String): List<Cocktail>?
}

