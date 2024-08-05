package com.example.mixingstat.data.network

import com.example.mixingstat.data.response.CocktailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailApiService {

    @GET("lookup.php")
    suspend fun getCocktail(@Query("i") id: String): Response<CocktailsResponse>

    @GET("random.php")
    suspend fun getRandomCocktail(): Response<CocktailsResponse>

    @GET("popular.php")
    suspend fun getAllPopularCocktails(): Response<CocktailsResponse>

    @GET("latest.php")
    suspend fun getAllLatestCocktails(): Response<CocktailsResponse>

    @GET("search.php")
    suspend fun searchCocktailsByFirstLetter(@Query("f") letter: Char): Response<CocktailsResponse>

    @GET("search.php")
    suspend fun searchCocktailByName(@Query("s") name: String): Response<CocktailsResponse>

    @GET("filter.php")
    suspend fun searchByIngredientName(@Query("i") name: String): Response<CocktailsResponse>
}