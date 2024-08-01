package com.example.mixingstat.presentation.state.home

import com.example.mixingstat.data.models.Cocktail

data class HomeScreenState(
    val popularCocktails: List<Cocktail> = emptyList(),
    val latestCocktails: List<Cocktail> = emptyList(),
    val suggestionCocktail: Cocktail? = null,
    val isLoading: Boolean = true
)