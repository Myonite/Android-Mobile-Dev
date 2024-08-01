package com.example.mixingstat.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private var BASE_URL =
        "https://www.thecocktaildb.com/api/json/v2/9973533/"

    val instance: CocktailApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(CocktailApiService::class.java)
    }
}