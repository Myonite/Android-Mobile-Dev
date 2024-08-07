package com.example.mixingstat.presentation.state.random

import com.example.mixingstat.data.models.Cocktail

data class RandomDrinkState(
    val showCocktail: Boolean = false,
    val selectedCocktail: Cocktail?,
    val errorMessage: String? = null,
)