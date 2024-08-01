package com.example.mixingstat.data.network

import com.example.mixingstat.data.models.Cocktail
import com.example.mixingstat.data.response.CocktailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
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
}