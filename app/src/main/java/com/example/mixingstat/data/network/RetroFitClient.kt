package com.example.mixingstat.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Singleton object that creates and provides an instance of the CocktailApiService.
 * This is used to make network requests to the Cocktail API.
 */
object RetrofitClient {
    // The base URL for the Cocktail API.
    private var BASE_URL =
        "https://www.thecocktaildb.com/api/json/v2/9973533/"

    /**
     * The instance of the CocktailApiService.
     * This is created lazily, meaning it is only created when it is first accessed.
     * It uses the Retrofit library to create the service.
     */
    val instance: CocktailApiService by lazy {
        // Create a Retrofit builder.
        val retrofit = Retrofit.Builder()
            // Set the base URL for the API.
            .baseUrl(BASE_URL)
            // Add a converter factory to convert the JSON responses into Kotlin objects.
            .addConverterFactory(GsonConverterFactory.create())
            // Build the Retrofit instance.
            .build()

        // Create the CocktailApiService from the Retrofit instance.
        retrofit.create(CocktailApiService::class.java)
    }
}