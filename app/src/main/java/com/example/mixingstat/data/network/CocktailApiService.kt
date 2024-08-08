package com.example.mixingstat.data.network

import com.example.mixingstat.data.response.CocktailsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * A Retrofit service to fetch data from the Cocktail API.
 */
interface CocktailApiService {

    /**
     * Fetch a cocktail by its ID.
     * @param id The ID of the cocktail.
     * @return A Response containing a CocktailsResponse.
     */
    @GET("lookup.php")
    suspend fun getCocktail(@Query("i") id: String): Response<CocktailsResponse>

    /**
     * Fetch a random cocktail.
     * @return A Response containing a CocktailsResponse.
     */
    @GET("random.php")
    suspend fun getRandomCocktail(): Response<CocktailsResponse>

    /**
     * Fetch all popular cocktails.
     * @return A Response containing a CocktailsResponse.
     */
    @GET("popular.php")
    suspend fun getAllPopularCocktails(): Response<CocktailsResponse>

    /**
     * Fetch all the latest cocktails.
     * @return A Response containing a CocktailsResponse.
     */
    @GET("latest.php")
    suspend fun getAllLatestCocktails(): Response<CocktailsResponse>

    /**
     * Search for cocktails by the first letter of their name.
     * @param letter The first letter of the cocktail name.
     * @return A Response containing a CocktailsResponse.
     */
    @GET("search.php")
    suspend fun searchCocktailsByFirstLetter(@Query("f") letter: Char): Response<CocktailsResponse>

    /**
     * Search for cocktails by their name.
     * @param name The name of the cocktail.
     * @return A Response containing a CocktailsResponse.
     */
    @GET("search.php")
    suspend fun searchCocktailByName(@Query("s") name: String): Response<CocktailsResponse>

    /**
     * Search for cocktails by ingredient name.
     * @param name The name of the ingredient.
     * @return A Response containing a CocktailsResponse.
     */
    @GET("filter.php")
    suspend fun searchByIngredientName(@Query("i") name: String): Response<CocktailsResponse>
}